package com.eventhub.UserProfileMicroService.dto;

import com.eventhub.UserProfileMicroService.models.Profile;

import java.util.List;

public class ActivitiesDTO {
    private String author;

    private String name;
    private String description;
    private List<String> tags;

    //todo: когда событие и до какого числа


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
}
