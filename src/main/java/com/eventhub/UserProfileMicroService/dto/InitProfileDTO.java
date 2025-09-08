package com.eventhub.UserProfileMicroService.dto;

import java.util.UUID;

public class InitProfileDTO {
    private UUID id;
    private String username;
    private String email;
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

    public String getEmail() {
        return email;
    }


}
