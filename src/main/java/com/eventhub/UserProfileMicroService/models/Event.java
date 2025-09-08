package com.eventhub.UserProfileMicroService.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
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
    private LocalDateTime time_of_event;

    @ManyToMany(mappedBy = "events")
    private final List<Profile> participants = new ArrayList<>();

    public Event() {}
    public Event(String name, String description, List<String> tags, int max_people, Profile author, LocalDateTime time_of_event) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.max_people = max_people;
        this.author = author;
        this.time_of_event = time_of_event;
    }

    public void addMember(Profile profile) {
        participants.add(profile);
        profile.getEvents().add(this);
    }

    @PreRemove //срабатывает перед eventRepo.delete(e)
    public void removeMembers() {
        this.participants
                .forEach(user -> user.getEvents().remove(this)
                );
    }

    public void removeMember(Profile profile) {
        this.participants.remove(profile);
        profile.getEvents().remove(this);
    }



    public List<String> getTags() {
        return tags;
    }
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
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

    public Profile getAuthor() {
        return author;
    }

    public List<Profile> getParticipants() {
        return participants;
    }

    public LocalDateTime getTime_of_event() {
        return time_of_event;
    }
}
