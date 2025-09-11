package com.bookmymovie.events.repository;

import com.bookmymovie.events.entities.SeatPricingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SeatPricingRepository extends JpaRepository<SeatPricingEntity, UUID> {
}