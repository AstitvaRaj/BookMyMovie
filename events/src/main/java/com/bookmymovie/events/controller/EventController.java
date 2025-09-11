package com.bookmymovie.events.controller;


import com.bookmymovie.events.dto.request.CreateEventDto;
import com.bookmymovie.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping
    public ResponseEntity<?> getEvents(@RequestParam(required = false, defaultValue = "all") String category,
                                       @RequestParam(required = false, defaultValue = "asc") String orderBy,
                                       @RequestParam(required = false, defaultValue = "English") String language,
                                       @RequestParam(required = false) String screenType,
                                       @RequestParam String city) {
        return eventService.getEvents(city, category, orderBy, language,screenType);
    }

    @PostMapping("admin/create")
    public ResponseEntity<?> createEvent(@RequestBody CreateEventDto eventDto, @RequestHeader String userid){
        return eventService.createEvents(eventDto, userid);
    }
}
