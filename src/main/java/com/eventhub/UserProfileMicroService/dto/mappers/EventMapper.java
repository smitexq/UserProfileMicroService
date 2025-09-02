package com.eventhub.UserProfileMicroService.dto.mappers;

import com.eventhub.UserProfileMicroService.dto.EventsDTO;
import com.eventhub.UserProfileMicroService.models.Event;
import com.eventhub.UserProfileMicroService.models.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventMapper {
    EventsDTO toDTO(Event event);

    // вспомогательный метод для преобразования профиля в строку
    default String mapProfileToString(Profile profile) {
        return profile.getUsername(); // только имя
    }
}
