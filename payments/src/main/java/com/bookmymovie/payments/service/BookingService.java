package com.bookmymovie.payments.service;

import com.bookmymovie.payments.dto.request.CreateBookingRequest;
import com.bookmymovie.payments.dto.response.BookingResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

public interface BookingService {

    BookingResponse createBooking(CreateBookingRequest bookingRequest, UUID username);

    Optional<BookingResponse> changeBookingStatus(CreateBookingRequest bookingRequest);

    ResponseEntity<?> cancelBooking(CreateBookingRequest bookingRequest);

    ResponseEntity<?> getHistory(String id, Integer cursor, Integer count);
}
