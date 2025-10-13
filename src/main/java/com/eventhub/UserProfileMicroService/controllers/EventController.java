package com.eventhub.UserProfileMicroService.controllers;

import com.eventhub.UserProfileMicroService.dto.ApiResponse;
import com.eventhub.UserProfileMicroService.dto.EventsDTO;
import com.eventhub.UserProfileMicroService.dto.NewEventDTO;
import com.eventhub.UserProfileMicroService.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile-service")
public class EventController {
    private final EventService eventSercice;

    public EventController(EventService eventSercice) {
        this.eventSercice = eventSercice;
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
    public ResponseEntity<ApiResponse<?>> signUpOnEvent(@RequestParam String username, @RequestParam String eventName) {
        ApiResponse<?> response = eventSercice.signUpOnEvent(username, eventName);
//        if (response.getBody() instanceof RequestNotificationDTO)
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

    @PutMapping("/leave_from_event/") //покинуть событие
    public ResponseEntity<String> leaveFromEvent(@RequestParam String username, @RequestParam String eventName) {
        return ResponseEntity.ok(eventSercice.leaveFromEvent(username, eventName));
    }



    @GetMapping("/all_events")
    public List<EventsDTO> getAllEvents() {
        return eventSercice.getAllEvents();
    }
}
