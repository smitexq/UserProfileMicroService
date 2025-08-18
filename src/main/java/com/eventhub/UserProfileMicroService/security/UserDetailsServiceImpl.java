package com.eventhub.UserProfileMicroService.security;

import com.eventhub.UserProfileMicroService.dao.ProfileRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    private final ProfileRepository repository;

    public UserDetailsServiceImpl(ProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(repository.findByUsername(username).get());
    }
}
