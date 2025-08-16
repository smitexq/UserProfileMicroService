package com.eventhub.UserProfileMicroService.controllers;

import com.eventhub.UserProfileMicroService.dto.ProfileDTO;
import com.eventhub.UserProfileMicroService.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile-service")
public class ProfileController {

    @GetMapping()
    public ResponseEntity<ProfileDTO> getProfile() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails;

    }

    @GetMapping("/my_events")
    public ResponseEntity<EventsDTO> getEventsPerUser() {
        
    }

    @GetMapping("/my_activities")
    public ResponseEntity<ActivitiesDTO> getActivitiesPerUser() {

    }

    @PostMapping
    public String addNewEvent(NewEventDTO newEvent) {

    }

}
