package com.eventhub.UserProfileMicroService.service;

import com.eventhub.UserProfileMicroService.dto.ActivitiesDTO;
import com.eventhub.UserProfileMicroService.dto.EventsDTO;
import com.eventhub.UserProfileMicroService.dto.InitProfileDTO;
import com.eventhub.UserProfileMicroService.dto.ProfileDTO;

import java.util.List;

public interface ProfileService {
    ProfileDTO getProfile(String username);
    void addNewProfile(InitProfileDTO initProfile);
    List<EventsDTO> getEventsPerUser(String username);
    ActivitiesDTO getActivitiesPerUser(String username);
}
