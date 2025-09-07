package com.bookmymovie.events.repository;

import com.bookmymovie.events.entities.SeatMap;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface SeatMapRepository extends Repository<SeatMap, UUID> {
}