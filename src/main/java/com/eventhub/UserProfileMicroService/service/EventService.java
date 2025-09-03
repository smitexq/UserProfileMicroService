package com.eventhub.UserProfileMicroService.service;

import com.eventhub.UserProfileMicroService.dto.NewEventDTO;

public interface EventService {
    String addNewEvent(String username, NewEventDTO newEvent);
    String editEvent(NewEventDTO newEvent);
    String deleteEvent(String owner_username, String eventName);
    String signUpOnEvent(String username, String eventName);
    String leaveFromEvent(String username, String eventName);
}
