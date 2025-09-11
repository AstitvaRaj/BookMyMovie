package com.bookmymovie.events.repository;

import com.bookmymovie.events.dto.enums.ScreenLayoutStatus;
import com.bookmymovie.events.entities.SeatMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SeatMapRepository extends JpaRepository<SeatMap, UUID> {

    long countByStatusAndScreenId(ScreenLayoutStatus status, UUID screenId);

}