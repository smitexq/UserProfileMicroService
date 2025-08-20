package com.eventhub.UserProfileMicroService.dto;

import java.util.UUID;

public class InitProfileDTO {
    private UUID id;
    private String username;
    private int age;

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }
}
