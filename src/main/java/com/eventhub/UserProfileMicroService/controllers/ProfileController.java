package com.eventhub.UserProfileMicroService.controllers;

import com.eventhub.UserProfileMicroService.dto.ProfileDTO;
import com.eventhub.UserProfileMicroService.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile-service")
public class ProfileController {

    private final ProfileService service;
    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<ProfileDTO> getProfile() {
        return ResponseEntity.ok(service.getProfile());
    }

    @PostMapping("/test")
    public void print() {
        System.out.println("Принял запрос");
    }

//    @GetMapping("/my_events")
//    public ResponseEntity<EventsDTO> getEventsPerUser() {
//
//    }
//
//    @GetMapping("/my_activities")
//    public ResponseEntity<ActivitiesDTO> getActivitiesPerUser() {
//
//    }
//
//    @PostMapping
//    public String addNewEvent(NewEventDTO newEvent) {
//
//    }

}
