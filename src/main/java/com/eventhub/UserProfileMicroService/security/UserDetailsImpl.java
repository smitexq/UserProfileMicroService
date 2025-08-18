package com.eventhub.UserProfileMicroService.security;

import com.eventhub.UserProfileMicroService.models.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private final Profile profile;
    public UserDetailsImpl(Profile profile) {
        this.profile = profile;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return profile.getUsername();
    }
}
