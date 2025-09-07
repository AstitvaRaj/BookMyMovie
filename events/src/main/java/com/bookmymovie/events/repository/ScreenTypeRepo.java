package com.bookmymovie.events.repository;

import com.bookmymovie.events.entities.ScreenTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenTypeRepo extends JpaRepository<ScreenTypeEntity, Long> {
}
