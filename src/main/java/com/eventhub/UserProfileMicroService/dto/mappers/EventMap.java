package com.eventhub.UserProfileMicroService.dto.mappers;

import com.eventhub.UserProfileMicroService.dto.EventsDTO;
import com.eventhub.UserProfileMicroService.models.Event;
import com.eventhub.UserProfileMicroService.models.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventMap {

    public EventsDTO toDTO(Event event) {
        if (event == null) return null;

        EventsDTO eventsDTO = new EventsDTO();

        eventsDTO.setName( event.getName() );
        eventsDTO.setDescription( event.getDescription() );
        eventsDTO.setMax_people( event.getMax_people() );

        List<String> list = event.getTags();
        if ( list != null ) {
            eventsDTO.setTags( new ArrayList<String>( list ) );
        }

        List<Profile> members = event.getParticipants();
        if ( members != null ) {
            eventsDTO.setMembers( new ArrayList<String>(members.stream()
                    .map(x -> x.getUsername())
                    .toList()) );
        }

        return eventsDTO;
    }
}
