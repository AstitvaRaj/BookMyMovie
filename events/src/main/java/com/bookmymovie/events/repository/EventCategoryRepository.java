package com.bookmymovie.events.repository;


import com.bookmymovie.events.entities.EventCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCategoryRepository extends JpaRepository<EventCategory, Long>, Pageable {
}
