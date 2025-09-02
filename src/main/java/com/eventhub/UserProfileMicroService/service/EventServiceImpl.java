package com.eventhub.UserProfileMicroService.service;

import com.eventhub.UserProfileMicroService.dao.EventRepository;
import com.eventhub.UserProfileMicroService.dao.ProfileRepository;
import com.eventhub.UserProfileMicroService.dto.NewEventDTO;
import com.eventhub.UserProfileMicroService.models.Event;
import com.eventhub.UserProfileMicroService.models.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepo;
    private final ProfileRepository profileRepo;

    public EventServiceImpl(EventRepository eventRepo, ProfileRepository profileRepo) {
        this.eventRepo = eventRepo;
        this.profileRepo = profileRepo;
    }


    @Override
    public String addNewEvent(String username, NewEventDTO newEvent) {
        String name = newEvent.getName();
        String desc = newEvent.getDescription();
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
                profileRepo.findByUsername(username).get()
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
    public String deleteEvent(String username, String eventName) {
        //username нужен для проверки того, что событие создал именно этот человек
        Optional<Profile> author = profileRepo.findByUsername(username);
        if (author.isEmpty()) return "Неизвестный пользователь";

        //Поиск в бд
        Optional<Event> event = eventRepo.findByName(eventName);
        if (event.isEmpty()) return "Неизвестное событие";


        if (!author.get().equals(
                event.get().getAuthor())
        ) return "Вы не являетесь создателем этого события";

        eventRepo.delete(event.get());
        return "Событие было удалено";
    }

    @Override
    public String signUpOnEvent(String username, String eventName) {
        Optional<Profile> user = profileRepo.findByUsername(username);
        if (user.isEmpty()) return "Пользователь не найден";

        Optional<Event> event = eventRepo.findByName(eventName);

        if (event.isPresent()) {
            String author_name = event.get().getAuthor().getUsername();
            if (author_name.equals(username)) return "Вы пытаетесь записаться на свое мероприятие";
            if (event.get().getParticipants().size() >= event.get().getMax_people()) return "Больше нет мест на запись";

            event.get().addMember(
                    user.get()
            );

            eventRepo.save(event.get());

            return String.format("Вы успешно записались на мероприятие %s", eventName);
        }

        return "Неизвестное мероприятие";
    }
}
