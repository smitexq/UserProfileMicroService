package com.eventhub.UserProfileMicroService.dto;

import com.eventhub.UserProfileMicroService.models.Event;

import java.util.List;

public class ProfileDTO {
    private String username;
    private int age;
    private List<Event> take_part_events;
//    private List<String> created_events;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Event> getTake_part_events() {
        return take_part_events;
    }

    public void setTake_part_events(List<Event> take_part_events) {
        this.take_part_events = take_part_events;
    }
}
