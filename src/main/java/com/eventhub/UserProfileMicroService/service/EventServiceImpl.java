package com.eventhub.UserProfileMicroService.service;

import com.eventhub.UserProfileMicroService.dao.EventRepository;
import com.eventhub.UserProfileMicroService.dao.ProfileRepository;
import com.eventhub.UserProfileMicroService.dto.ApiResponse;
import com.eventhub.UserProfileMicroService.dto.EventsDTO;
import com.eventhub.UserProfileMicroService.dto.NewEventDTO;
import com.eventhub.UserProfileMicroService.dto.mappers.EventMap;
import com.eventhub.UserProfileMicroService.models.Event;
import com.eventhub.UserProfileMicroService.models.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepo;
    private final ProfileRepository profileRepo;
    private final MailService mailService;
    private final EventMap eventMapper;

    public EventServiceImpl(EventRepository eventRepo, ProfileRepository profileRepo, MailService mailService, EventMap eventMapper) {
        this.eventRepo = eventRepo;
        this.profileRepo = profileRepo;
        this.mailService = mailService;
        this.eventMapper = eventMapper;
    }


    @Override
    public List<EventsDTO> getAllEvents() {
        return eventRepo.findAll()
                .stream()
                .filter(event -> event.getParticipants().size() < event.getMax_people()) //только события, в которых еще есть места
                .map(event -> eventMapper.toDTO(event))
                .collect(Collectors.toList());
    }

    @Override
    public String addNewEvent(String username, NewEventDTO newEvent) {
        String name = newEvent.getName();
        String desc = newEvent.getDescription();
        LocalDateTime time = newEvent.getTime_of_event();
        ArrayList<String> tags = (ArrayList<String>) newEvent.getTags();
        int people = newEvent.getMax_people();

        //Проверка, что такое событие еще не существует (по имени), кол-во людей (минимум 2), описание НЕ пустое

        if (eventRepo.findByName(name)
                .isPresent()) {
            return "Событие с таким названием уже существует";
        }
        if (desc == null || desc.isBlank() ) return "Заполните описание";
        if (!(people > 1)) return "Участников должно быть минимум 2";

        Event event = new Event(
                name,
                desc,
                tags,
                people,
                profileRepo.findByUsername(username).get(),
                time
        );
        eventRepo.save(event);
        return "Событие зарегистрировано!";
    }

    @Override
    public String editEvent(NewEventDTO event) {
        String name = event.getName();
        String desc = event.getDescription();
        ArrayList<String> tags = (ArrayList<String>) event.getTags();
        int people = event.getMax_people();

        //Поиск в бд
        Optional<Event> in_database = eventRepo.findByName(name);

//        in_database.ifPresent(e -> eventRepo.save(
//                e.setMeta(
//                        name,
//                        desc,
//                        tags,
//                        people
//                )
//                ));;

        if (in_database.isPresent()) {
            Event cur_event = in_database.get();

            cur_event.setName(name);
            cur_event.setDescription(desc);
            cur_event.setTags(tags);
            cur_event.setMax_people(people);
            eventRepo.save(cur_event);

            return String.format("Успешное редактирование события %s", name);

        } else return "Событиые с таким именем не найдено";
    }

    @Override
    public String deleteEvent(String owner_username, String eventName) {
        //username нужен для проверки того, что событие создал именно этот человек
        Optional<Profile> author = profileRepo.findByUsername(owner_username);
        if (author.isEmpty()) return "Неизвестный пользователь";

        //Поиск в бд
        Optional<Event> var_event = eventRepo.findByName(eventName);
        if (var_event.isEmpty()) return "Неизвестное событие";
        Event event = var_event.get();


        if (!author.get().equals(
                event.getAuthor())
        ) return "Вы не являетесь создателем этого события";

        //Очищаем связи (удаляем всех из события)
        event.getParticipants().forEach(user -> {
            user.getEvents().remove(event);
            //Запрос на удаления уведомления
            mailService.sendPostRequestWithNoResponse(
                    "/mail-service/remove_notification",
                    Map.of(
                            "username", user.getUsername(),
                            "event_name", eventName
                    )
            );}
        );
        //Удаляем мероприятия
        eventRepo.delete(event);

        return "Событие было удалено";
    }


    @Override
    public ApiResponse<?> signUpOnEvent(String username, String eventName) {
        Optional<Profile> user = profileRepo.findByUsername(username);
        if (user.isEmpty()) return errorResponse(404,"Пользователь не найден");

        Optional<Event> var_event = eventRepo.findByName(eventName);

        if (var_event.isPresent()) {
            Event event = var_event.get();
            String author_name = event.getAuthor().getUsername();

            if (author_name.equals(username)) return errorResponse(406,"Вы пытаетесь записаться на свое мероприятие");
            if (event.getParticipants().contains(user.get())) return errorResponse(406, String.format("Вы уже зарегестрированы на событие %s", eventName));
            if (event.getParticipants().size() >= event.getMax_people()) return errorResponse(406,"Больше нет мест на запись");

            event.addMember(
                    user.get()
            );

            eventRepo.save(event);

            return new ApiResponse<>(HttpStatus.OK,
                    String.format("Вы успешно записались на мероприятие %s", eventName),
                    Map.of(
                            "email", user.get().getEmail(),
                            "time_of_event", event.getTime_of_event()
                    )
            );
        }

        return errorResponse(404, "Неизвестное мероприятие");
    }

    private ApiResponse<?> errorResponse(int statusCode, String msg) {
        return new ApiResponse<>(
                HttpStatus.valueOf(statusCode),
                msg,
                null);
    }



    @Override
    public String leaveFromEvent(String username, String eventName) {
        Optional<Profile> var_user = profileRepo.findByUsername(username);

        if (var_user.isEmpty()) return "Пользователь не найден";
        Profile user = var_user.get();

        Optional<Event> var_event = eventRepo.findByName(eventName);
        if (var_event.isPresent()) {
            if (!user.getEvents().contains(var_event.get())) return String.format("Вы не участвуете в мероприятии %s", eventName);

            var_event.get().removeMember(user);

            eventRepo.save(var_event.get());

            //Запрос на удаления уведомления
            mailService.sendPostRequestWithNoResponse(
                    "/mail-service/remove_notification",
                    Map.of(
                            "username", user.getUsername(),
                            "event_name", eventName
                    )
            );

            return String.format("Вы покинули мероприятие %s", eventName);
        }

        return "Неизвестное мероприятие";
    }
}
