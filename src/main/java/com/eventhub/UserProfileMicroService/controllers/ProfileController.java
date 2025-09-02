package com.eventhub.UserProfileMicroService.controllers;

import com.eventhub.UserProfileMicroService.dto.*;
import com.eventhub.UserProfileMicroService.service.EventServiceImpl;
import com.eventhub.UserProfileMicroService.service.ProfileServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile-service")
public class ProfileController {

    private final ProfileServiceImpl profileService;
    private final EventServiceImpl eventSercice;

    public ProfileController(ProfileServiceImpl profileService, EventServiceImpl eventSercice) {
        this.profileService = profileService;
        this.eventSercice = eventSercice;
    }


    @GetMapping("/get_profile/{username}") //информация о профиле самого пользователя
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable String username) {
        return ResponseEntity.ok(profileService.getProfile(username));
    }

    @PostMapping("/add_profile")
    public void addNewProfile(@RequestBody InitProfileDTO initProfile) {
        System.out.println("Получил");
        profileService.addNewProfile(initProfile);
    }

    @PostMapping("/create_event/{username}")
    public ResponseEntity<String> addNewEvent(@PathVariable String username, @RequestBody NewEventDTO newEvent) {
        return ResponseEntity.ok(eventSercice.addNewEvent(username, newEvent));
    }

    @PutMapping("/edit_event")
    public ResponseEntity<String> editEvent(@RequestBody NewEventDTO event) {
        return ResponseEntity.ok(eventSercice.editEvent(event));
    }

    @DeleteMapping("/delete_event/")
    public ResponseEntity<String> deleteEvent(@RequestParam String username, @RequestParam String eventName) {
        return ResponseEntity.ok(eventSercice.deleteEvent(username, eventName));
    }

    @PutMapping("/sign_up_on_event/") //запись на событие
    public ResponseEntity<String> signUpOnEvent(@RequestParam String username, @RequestParam String eventName) {
        return ResponseEntity.ok(eventSercice.signUpOnEvent(username, eventName));
    }

    @GetMapping("/my_events/{username}") //События которые создал пользователь
    public ResponseEntity<List<EventsDTO>> getEventsPerUser(@PathVariable String username) {
        return ResponseEntity.ok(profileService.getEventsPerUser(username));
    }

    @GetMapping("/my_activities") //события на которые пользователь записан
    public ResponseEntity<ActivitiesDTO> getActivitiesPerUser(@RequestBody String username) {
        return ResponseEntity.ok(profileService.getActivitiesPerUser(username));
    }
}
