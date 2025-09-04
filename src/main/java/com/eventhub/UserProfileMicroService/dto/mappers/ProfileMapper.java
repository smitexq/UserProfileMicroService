package com.eventhub.UserProfileMicroService.dto.mappers;

import com.eventhub.UserProfileMicroService.dto.ProfileDTO;
import com.eventhub.UserProfileMicroService.models.Event;
import com.eventhub.UserProfileMicroService.models.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileMapper {

    public ProfileDTO toDTO(Profile profile) {
        if (profile == null) return null;

        ProfileDTO profileDTO = new ProfileDTO(
                profile.getUsername(),
                profile.getAge()
        );

        List<Event> profile_events = profile.getEvents();
        if (!profile_events.isEmpty()) {
            profileDTO.setTake_part_events(profile_events.stream()
                    .map(x -> x.getName())
                    .toList()
            );
        }

        return profileDTO;
    }
}
