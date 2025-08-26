package com.eventhub.UserProfileMicroService.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Profile author;

    private String name;
    private String description;
    private List<String> tags;
    private int max_people;
    //todo: Поля с количеством участников (их имена) И до какого числа событие И когда событие

    @ManyToMany(mappedBy = "events")
    private ArrayList<Profile> participants = new ArrayList<>();


    public Event(String name, String description, List<String> tags, int max_people, Profile author) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.max_people = max_people;
        this.author = author;
    }

    public List<String> getTags() {
        return tags;
    }
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }

    public Profile getAuthor() {
        return author;
    }
}
