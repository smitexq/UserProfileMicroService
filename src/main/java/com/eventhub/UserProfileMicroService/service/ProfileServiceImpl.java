package com.eventhub.UserProfileMicroService.service;

import com.eventhub.UserProfileMicroService.dao.EventRepository;
import com.eventhub.UserProfileMicroService.dao.ProfileRepository;
import com.eventhub.UserProfileMicroService.dto.ActivitiesDTO;
import com.eventhub.UserProfileMicroService.dto.EventsDTO;
import com.eventhub.UserProfileMicroService.dto.InitProfileDTO;
import com.eventhub.UserProfileMicroService.dto.ProfileDTO;
import com.eventhub.UserProfileMicroService.dto.mappers.ActivitiesMapper;
import com.eventhub.UserProfileMicroService.dto.mappers.EventMap;
import com.eventhub.UserProfileMicroService.dto.mappers.ProfileMapper;
import com.eventhub.UserProfileMicroService.models.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepo;
    private final ProfileMapper profileMapper;
    private final EventRepository eventRepo;
    private final EventMap eventMap;
    private final ActivitiesMapper activitiesMap;

    public ProfileServiceImpl(ProfileRepository profileRepo, ProfileMapper profileMapper, EventRepository eventRepo, EventMap eventMap, ActivitiesMapper activitiesMap) {
        this.profileRepo = profileRepo;
        this.profileMapper = profileMapper;
        this.eventRepo = eventRepo;
        this.eventMap = eventMap;
        this.activitiesMap = activitiesMap;
    }

    @Override
    public ProfileDTO getProfile(String username) {
        //Пользователь точно есть, так как username передается при проверке jwt ключа в ApiGateway
        return profileMapper.toDTO(
                profileRepo.findByUsername(username).get()
        );
    }

    @Override
    public void addNewProfile(InitProfileDTO initProfile) {
        Profile new_profile = new Profile(initProfile);
        System.out.println(initProfile.getUsername() + " " + initProfile.getId().toString() + " " + initProfile.getAge());
        profileRepo.save(new_profile);
    }

    @Override
    public List<EventsDTO> getEventsPerUser(String username) {
        return eventRepo.findAll()
                .stream()
                .filter(e -> e.getAuthor().getUsername().equals(username))
                .map(eventMap::toDTO)
                .toList();
    }

    @Override
    public List<ActivitiesDTO> getActivitiesPerUser(String username) {
        Profile profile = profileRepo.findByUsername(username).get();

        return eventRepo.findAll()
                .stream()
                .filter(event -> event.getParticipants()
                        .contains(profile))
                .map(e -> activitiesMap.toDTO(e))
                .toList();
    }
}
