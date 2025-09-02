package com.eventhub.UserProfileMicroService.dto;

import java.util.List;

public class EventsDTO {
    private String name;
    private String description;
    private List<String> tags;
    private int max_people;

    private List<String> members;

    //todo: Поля с количеством участников (их имена) И до какого числа событие


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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
