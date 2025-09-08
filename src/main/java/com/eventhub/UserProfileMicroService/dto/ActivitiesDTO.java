package com.eventhub.UserProfileMicroService.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ActivitiesDTO {
    private String author;

    private String name;
    private String description;
    private List<String> tags;
    private LocalDateTime time_of_event;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public LocalDateTime getTime_of_event() {
        return time_of_event;
    }

    public void setTime_of_event(LocalDateTime time_of_event) {
        this.time_of_event = time_of_event;
    }
}
