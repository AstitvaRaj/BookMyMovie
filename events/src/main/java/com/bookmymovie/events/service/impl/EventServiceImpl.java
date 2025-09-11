package com.bookmymovie.events.service.impl;

import com.bookmymovie.events.dto.enums.EventStatus;
import com.bookmymovie.events.dto.request.CreateEventDto;
import com.bookmymovie.events.entities.EventLanguage;
import com.bookmymovie.events.entities.Events;
import com.bookmymovie.events.repository.EventsRepository;
import com.bookmymovie.events.repository.LanguageMasterRepository;
import com.bookmymovie.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventsRepository eventsRepository;

    @Autowired
    LanguageMasterRepository languageMasterRepository;

    @Override
    public ResponseEntity<?> getEvents(String city, String category, String orderBy, String language, String screenType) {

        return ResponseEntity.ok(eventsRepository.findAll());
    }

    @Override
    public ResponseEntity<?> createEvents(CreateEventDto eventDto, String userid) {
        try{
            Events eventEntity = eventDto.getEventEntity(UUID.fromString(userid));

            List<EventLanguage> list = languageMasterRepository.findAllById(eventDto.getLanguages())
                    .stream()
                    .map(languageMaster -> {
                        EventLanguage eventLanguage = new EventLanguage();
                        eventLanguage.setLanguageMaster(languageMaster);
                        eventLanguage.setEvents(eventEntity);
                        eventLanguage.setStatus(EventStatus.SCHEDULED);
                        return eventLanguage;
                    })
                    .toList();
            eventEntity.setEventLanguages(list);
            eventsRepository.save(eventEntity);
            return ResponseEntity.ok("Event Created Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
