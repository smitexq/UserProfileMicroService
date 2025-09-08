package com.eventhub.UserProfileMicroService.dto;

import java.time.LocalDateTime;
import java.util.List;

public class NewEventDTO {
    private String name;
    private String description;
    private List<String> tags;
    private int max_people;
    private LocalDateTime time_of_event;

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

    public LocalDateTime getTime_of_event() {
        return time_of_event;
    }
}
