package com.eventhub.UserProfileMicroService.dto.mappers;

import com.eventhub.UserProfileMicroService.dto.ActivitiesDTO;
import com.eventhub.UserProfileMicroService.models.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActivitiesMapper {

    public ActivitiesDTO toDTO(Event event) {
        if (event == null) return null;

        ActivitiesDTO activity = new ActivitiesDTO();
        activity.setAuthor(event.getAuthor().getUsername());
        activity.setName(event.getName());
        activity.setDescription(event.getDescription());

        List<String> tags = event.getTags();
        if (tags != null) {
            activity.setTags(new ArrayList<>(tags));
        }


        return activity;
    }
}
