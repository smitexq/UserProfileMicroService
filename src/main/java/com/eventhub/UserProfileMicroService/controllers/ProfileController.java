package com.eventhub.UserProfileMicroService.controllers;

import com.eventhub.UserProfileMicroService.dto.*;
import com.eventhub.UserProfileMicroService.service.EventService;
import com.eventhub.UserProfileMicroService.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile-service")
public class ProfileController {

    private final ProfileService profileService;
    private final EventService eventSercice;

    public ProfileController(ProfileService profileService, EventService eventSercice) {
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
    @PutMapping("/leave_from_event/") //покинуть событие
    public ResponseEntity<String> leaveFromEvent(@RequestParam String username, @RequestParam String eventName) {
        return ResponseEntity.ok(eventSercice.leaveFromEvent(username, eventName));
    }

    @GetMapping("/my_events/{username}") //События которые создал пользователь
    public ResponseEntity<List<EventsDTO>> getEventsPerUser(@PathVariable String username) {
        return ResponseEntity.ok(profileService.getEventsPerUser(username));
    }

    @GetMapping("/my_activities/{username}") //события на которые пользователь записан
    public ResponseEntity<List<ActivitiesDTO>> getActivitiesPerUser(@PathVariable String username) {
        return ResponseEntity.ok(profileService.getActivitiesPerUser(username));
    }
}
