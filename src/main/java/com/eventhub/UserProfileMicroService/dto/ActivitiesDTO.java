package com.eventhub.UserProfileMicroService.dto;

import com.eventhub.UserProfileMicroService.models.Profile;

import java.util.List;

public class ActivitiesDTO {
    private Profile author;

    private String name;
    private String description;
    private List<String> tags;

    //todo: когда событие и до какого числа
}
