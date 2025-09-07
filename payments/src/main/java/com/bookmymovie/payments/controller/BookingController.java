package com.bookmymovie.payments.controller;

import com.bookmymovie.payments.dto.request.CreateBookingRequest;
import com.bookmymovie.payments.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PutMapping("/cancel")
    public ResponseEntity<?> cancelBooking(@RequestBody CreateBookingRequest bookingRequest){
        return bookingService.cancelBooking(bookingRequest);
    }

    @GetMapping("/history")
    public ResponseEntity<?> getBookingHistory(@RequestParam String id, @RequestParam(required = false) Integer cursor, @RequestParam(required = false) Integer count){
        return bookingService.getHistory(id, cursor, count);
    }
}
