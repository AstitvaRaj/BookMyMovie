package com.bookmymovie.events.repository;

import com.bookmymovie.events.entities.Screens;
import com.bookmymovie.events.entities.enums.VenueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScreensRepository extends JpaRepository<Screens, UUID> {

    @Query("select s from Screens s where s.venue = ?1 and upper(s.screenName) = upper(?2) and s.status = ?3")
    List<Screens> findByVenueAndScreenNameIgnoreCaseAndStatus(UUID venue, String screenName, VenueStatus status);

}