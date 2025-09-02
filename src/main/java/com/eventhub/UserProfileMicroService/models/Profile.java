package com.eventhub.UserProfileMicroService.models;

import com.eventhub.UserProfileMicroService.dto.InitProfileDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Profile {
    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private int age;

    @ManyToMany
    @JoinTable(
            name = "profile_events",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events = new ArrayList<>();


    public Profile() {}
    public Profile(InitProfileDTO profile) {
        this.id = profile.getId();
        this.username = profile.getUsername();
        this.age = profile.getAge();
    }

    @Override
    public String toString() {
        return "Пользователь " + username;
    }


    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public List<Event> getEvents() {
        return events;
    }
}