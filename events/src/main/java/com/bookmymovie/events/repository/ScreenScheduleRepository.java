package com.bookmymovie.events.repository;

import com.bookmymovie.events.entities.ScreenSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScreenScheduleRepository extends JpaRepository<ScreenSchedule, UUID> {
}