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


    @GetMapping("/get_profile")
    public ResponseEntity<ProfileDTO> getProfile() {
        return ResponseEntity.ok(service.getProfile());
    }

    @PostMapping("/add_profile")
    public void addNewProfile(@RequestBody InitProfileDTO initProfile) {
        System.out.println("Получил");
        service.addNewProfile(initProfile);
    }

    @PostMapping("/create_event")
    public String addNewEvent(NewEventDTO newEvent) {
        return service.addNewEvent(newEvent);
    }

    @GetMapping("/my_events") //События которые создал пользователь
    public ResponseEntity<EventsDTO> getEventsPerUser() {
        return service.getEventsPerUser();
    }

    @GetMapping("/my_activities") //события на которые пользователь записан
    public ResponseEntity<ActivitiesDTO> getActivitiesPerUser() {
        return service.getActivitiesPerUser();
    }

    @PutMapping("/edit_event/{id}")
    public String editEvent(NewEventDTO newEvent, @PathVariable long id) {
        return service.editEvent(newEvent, id);
    }

    @DeleteMapping("/delete_event/{id}")
    public String deleteEvent(@PathVariable long id) {
        return service.deleteEvent(id);
    }
}
