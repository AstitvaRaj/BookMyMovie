package com.bookmymovie.events.service;


import com.bookmymovie.events.dto.request.CreateEventDto;
import org.springframework.http.ResponseEntity;

public interface EventService {

    ResponseEntity<?> getEvents(String city, String category, String orderBy, String language, String screenType);

    ResponseEntity<?> createEvents(CreateEventDto eventDto, String userid);
}
