package com.eventhub.UserProfileMicroService.dto;

import com.eventhub.UserProfileMicroService.models.Event;

import java.util.ArrayList;

public class ProfileDTO {
    private String username;
    private String email;
    private int age;
    private ArrayList<Event> events;

}
