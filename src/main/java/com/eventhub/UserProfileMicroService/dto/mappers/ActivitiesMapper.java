package com.eventhub.UserProfileMicroService.dto.mappers;

import com.eventhub.UserProfileMicroService.dto.ActivitiesDTO;
import com.eventhub.UserProfileMicroService.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActivitiesMapper {
    ActivitiesDTO toDTO(Event event);
}
