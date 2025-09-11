package com.bookmymovie.events.repository;

import com.bookmymovie.events.entities.Events;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface EventsRepository extends JpaRepository<Events, UUID> , JpaSpecificationExecutor<Events> {


}