package com.bookmymovie.events.repository;

import com.bookmymovie.events.entities.EventLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventLanguageRepository extends JpaRepository<EventLanguage, UUID> {
}