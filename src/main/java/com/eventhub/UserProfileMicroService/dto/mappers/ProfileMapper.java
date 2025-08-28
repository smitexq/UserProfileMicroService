package com.eventhub.UserProfileMicroService.dto.mappers;

import com.eventhub.UserProfileMicroService.dto.ProfileDTO;
import com.eventhub.UserProfileMicroService.models.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfileMapper {
    ProfileDTO toDTO(Profile profile);
}
