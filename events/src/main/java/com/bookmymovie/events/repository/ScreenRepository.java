package com.bookmymovie.events.repository;

import com.bookmymovie.events.entities.ScreenSchedule;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface ScreenRepository extends Repository<ScreenSchedule, UUID> {
}