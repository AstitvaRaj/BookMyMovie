package com.bookmymovie.events.controller;


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
                                       @RequestParam String city) {
        return eventService.getEvents(city, category, orderBy);
    }

    @PostMapping("create")
    public ResponseEntity<?> createEvent(){
        return null;
    }
}
