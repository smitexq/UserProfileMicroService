package com.eventhub.UserProfileMicroService.service;

import com.eventhub.UserProfileMicroService.dto.NewEventDTO;

public interface EventService {
    String addNewEvent(String username, NewEventDTO newEvent);
    String editEvent(NewEventDTO newEvent);
    String deleteEvent(String username, String eventName);
}
