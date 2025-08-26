package com.eventhub.UserProfileMicroService.service;

import com.eventhub.UserProfileMicroService.dao.ProfileRepository;
import com.eventhub.UserProfileMicroService.dto.ActivitiesDTO;
import com.eventhub.UserProfileMicroService.dto.EventsDTO;
import com.eventhub.UserProfileMicroService.dto.InitProfileDTO;
import com.eventhub.UserProfileMicroService.dto.ProfileDTO;
import com.eventhub.UserProfileMicroService.models.Profile;
import com.eventhub.UserProfileMicroService.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepo;
    public ProfileServiceImpl(ProfileRepository profileRepo) {
        this.profileRepo = profileRepo;
    }

    @Override
    public ProfileDTO getProfile(String username) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }

    @Override
    public void addNewProfile(InitProfileDTO initProfile) {
        Profile new_profile = new Profile(initProfile);
        System.out.println(initProfile.getUsername() + " " + initProfile.getId().toString() + " " + initProfile.getAge());
        profileRepo.save(new_profile);
    }

    @Override
    public EventsDTO getEventsPerUser(String username) {
        return null;
    }

    @Override
    public ActivitiesDTO getActivitiesPerUser(String username) {
        return null;
    }
}
