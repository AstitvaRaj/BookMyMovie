package com.bookmymovie.events.service;


import org.springframework.http.ResponseEntity;

public interface EventService {

    ResponseEntity<?> getEvents(String city, String category, String orderBy);

    ResponseEntity<?> createEvents(String city, String category, String orderBy);
}
