package com.eventhub.UserProfileMicroService.models;

import jakarta.persistence.*;

import java.util.ArrayList;
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
    private ArrayList<Event> events = new ArrayList<>();


    public Profile() {}
    public Profile(String username, String email, String password) {
        setUsername(username);
    }



    public UUID getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return null;
    }
}