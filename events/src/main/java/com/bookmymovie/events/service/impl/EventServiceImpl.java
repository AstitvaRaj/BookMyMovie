package com.bookmymovie.events.service.impl;

import com.bookmymovie.events.repository.EventsRepository;
import com.bookmymovie.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventsRepository eventsRepository;

    @Override
    public ResponseEntity<?> getEvents(String city, String category, String orderBy) {
        return ResponseEntity.ok(eventsRepository.findAll());
    }

    @Override
    public ResponseEntity<?> createEvents(String city, String category, String orderBy) {
        return null;
    }
}
