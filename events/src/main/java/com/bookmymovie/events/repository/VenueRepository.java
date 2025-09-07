package com.bookmymovie.events.repository;

import com.bookmymovie.events.entities.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VenueRepository extends JpaRepository<VenueEntity, UUID> {
}