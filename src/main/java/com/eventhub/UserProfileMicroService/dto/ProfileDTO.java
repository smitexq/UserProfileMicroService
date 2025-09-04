package com.eventhub.UserProfileMicroService.dto;

import java.util.List;

public class ProfileDTO {
    private String username;
    private int age;
    private List<String> take_part_events;
//    private List<String> created_events;

    public ProfileDTO(String username, int age) {
        this.username = username;
        this.age = age;
    }

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

    public List<String> getTake_part_events() {
        return take_part_events;
    }

    public void setTake_part_events(List<String> take_part_events) {
        this.take_part_events = take_part_events;
    }
}
