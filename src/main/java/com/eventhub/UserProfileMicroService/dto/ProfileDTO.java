package com.eventhub.UserProfileMicroService.dto;

import com.eventhub.UserProfileMicroService.models.Event;

import java.util.List;

public class ProfileDTO {
    private String username;
    private int age;
    private List<Event> events;

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


}
