package com.eventhub.UserProfileMicroService.models;

import java.util.ArrayList;

public class Event {
    private String name;
    private String description;
    private ArrayList<String> tags;
    private int max_people = 3;



    public ArrayList<String> getTags() {
        return tags;
    }
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
    public void addTag(String tag) {
        getTags().add(tag);
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

    public int getMax_people() {
        return max_people;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }
}
