package com.eventhub.UserProfileMicroService.dao;

import com.eventhub.UserProfileMicroService.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM event AS e WHERE e.name = :name"
    )
    Optional<Event> findByName(@Param("name") String name);
}
