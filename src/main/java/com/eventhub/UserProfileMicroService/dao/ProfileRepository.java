package com.eventhub.UserProfileMicroService.dao;

import com.eventhub.UserProfileMicroService.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    @Query(
       nativeQuery = true,
       value = "SELECT * FROM profile AS p WHERE p.username = :username"
    )
    Optional<Profile> findByUsername(@Param("username") String username);
}
