package com.bookmymovie.events.controller;

import com.bookmymovie.events.dto.request.*;
import com.bookmymovie.events.service.ScheduleService;
import com.bookmymovie.events.service.ScreenService;
import com.bookmymovie.events.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class VenueController {

    @Autowired
    VenueService venueService;

    @Autowired
    ScreenService screenService;

    @Autowired
    ScheduleService scheduleService;

    @PostMapping("admin/venue/change-owner")
    public ResponseEntity<?> changeOwner(@RequestBody AddVenueDto venue, @RequestHeader String userId) {
        return venueService.changeOwner(venue, userId);
    }

    @PostMapping("admin/venue/add")
    public ResponseEntity<?> createVenue(@RequestBody AddVenueDto venue, @RequestHeader String userId) {
        return venueService.createVenue(venue, userId);
    }

    @PostMapping("admin/venue/screen/create")
    public ResponseEntity<?> createScreen(@RequestBody ScreenCreateDto screenCreateDto, @RequestHeader String userId) {
        return screenService.createScreen(screenCreateDto, userId);
    }

    @PostMapping("admin/venue/screen/add-layout")
    public ResponseEntity<?> createSeatLayout(@RequestBody ScreenLayoutCreateDto layout) {
        return screenService.createSeatLayout(layout);
    }

    @PostMapping("admin/venue/schedule-event")
    public ResponseEntity<?> scheduleEvent(@RequestBody List<ScheduleEventDto> eventDto) {
        return scheduleService.scheduleEvent(eventDto);
    }

    @PostMapping("admin/venue/screen/update-prices")
    public ResponseEntity<?> updatePrices(@RequestBody  List<UpdateSeatPricesDto> updateSeatPricesDto) {
        return scheduleService.updateSeatPrices(updateSeatPricesDto);
    }

}
