package com.eventhub.UserProfileMicroService.controllers;

import com.eventhub.UserProfileMicroService.dto.InitProfileDTO;
import com.eventhub.UserProfileMicroService.dto.ProfileDTO;
import com.eventhub.UserProfileMicroService.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add_profile")
    public void addNewProfile(@RequestBody InitProfileDTO initProfile) {
        System.out.println("Получил");
        service.addNewProfile(initProfile);
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
