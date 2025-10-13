package com.eventhub.UserProfileMicroService.service;

import com.eventhub.UserProfileMicroService.dto.ApiResponse;
import com.eventhub.UserProfileMicroService.dto.EventsDTO;
import com.eventhub.UserProfileMicroService.dto.NewEventDTO;

import java.util.List;

public interface EventService {
    String addNewEvent(String username, NewEventDTO newEvent);
    String editEvent(NewEventDTO newEvent);
    String deleteEvent(String owner_username, String eventName);
    ApiResponse<?> signUpOnEvent(String username, String eventName);
    String leaveFromEvent(String username, String eventName);

    List<EventsDTO> getAllEvents();
}
