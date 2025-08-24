package com.eventhub.UserProfileMicroService.service;

import com.eventhub.UserProfileMicroService.dao.ProfileRepository;
import com.eventhub.UserProfileMicroService.dto.InitProfileDTO;
import com.eventhub.UserProfileMicroService.dto.ProfileDTO;
import com.eventhub.UserProfileMicroService.models.Profile;
import com.eventhub.UserProfileMicroService.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository repository;
    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }


    public ProfileDTO getProfile() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }

    public void addNewProfile(InitProfileDTO initProfile) {
        Profile new_profile = new Profile(initProfile);
        System.out.println(initProfile.getUsername() + " " + initProfile.getId().toString() + " " + initProfile.getAge());
        repository.save(new_profile);
    }
}
