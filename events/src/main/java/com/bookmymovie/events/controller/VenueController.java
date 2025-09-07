package com.bookmymovie.events.controller;

import com.bookmymovie.events.dto.request.AddVenueDto;
import com.bookmymovie.events.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class VenueController {

    @Autowired
    VenueService venueService;

    @PostMapping("admin/venue/change-owner")
    public ResponseEntity<?> changeOwner(@RequestBody AddVenueDto venue, @RequestHeader String userId){
        return venueService.changeOwner(venue, userId);
    }

    @PostMapping("admin/venue/add")
    public ResponseEntity<?> createVenue(@RequestBody AddVenueDto venue, @RequestHeader String userId){
        return venueService.createVenue(venue, userId);
    }

    @PostMapping("admin/venue/screen/add-layout")
    public ResponseEntity<?> createSeatLayout(@RequestBody Map<String, String> map){
        return null;
    }

    @PostMapping("admin/venue/schedule-event")
    public ResponseEntity<?> scheduleEvent(@RequestBody Map<String, String> map){
        return null;
    }

    @PostMapping("admin/venue/screen/assign-price")
    public ResponseEntity<?> createAssignPrice(@RequestBody Map<String, String> map){
        return null;
    }

    @PostMapping("/venue/get")
    public ResponseEntity<?> getVenue(@RequestBody Map<String, String> map){
        return null;
    }

}
