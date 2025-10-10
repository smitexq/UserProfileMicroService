package com.eventhub.UserProfileMicroService.controllers;

import com.eventhub.UserProfileMicroService.dto.ActivitiesDTO;
import com.eventhub.UserProfileMicroService.dto.EventsDTO;
import com.eventhub.UserProfileMicroService.dto.InitProfileDTO;
import com.eventhub.UserProfileMicroService.dto.ProfileDTO;
import com.eventhub.UserProfileMicroService.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile-service")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }


    @GetMapping("/get_profile/{username}") //информация о профиле самого пользователя
    public ProfileDTO getProfile(@PathVariable String username) {
        return profileService.getProfile(username);
    }

    @PostMapping("/add_profile")
    public void addNewProfile(@RequestBody InitProfileDTO initProfile) {
        profileService.addNewProfile(initProfile);
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
