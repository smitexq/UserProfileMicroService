package com.eventhub.UserProfileMicroService.dto;

import java.util.List;

public class NewEventDTO {
    private String name;
    private String description;
    private List<String> tags;
    private int max_people;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    public int getMax_people() {
        return max_people;
    }
}
