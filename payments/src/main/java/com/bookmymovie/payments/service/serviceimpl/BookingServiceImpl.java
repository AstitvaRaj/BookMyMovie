package com.bookmymovie.payments.service.serviceimpl;

import com.bookmymovie.payments.dto.request.CreateBookingRequest;
import com.bookmymovie.payments.dto.response.BookingResponse;
import com.bookmymovie.payments.entity.BookingDetails;
import com.bookmymovie.payments.enums.BookingStatus;
import com.bookmymovie.payments.repository.BookingDetailsRepository;
import com.bookmymovie.payments.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingDetailsRepository bookingDetailsRepository;

    @Override
    public BookingResponse createBooking(CreateBookingRequest bookingRequest, UUID username) {
        BookingDetails bookingEntity = new BookingDetails();
        bookingEntity.setUserId(username);
        bookingEntity.setSeatCode(bookingRequest.getSeatCode());
        bookingEntity.setScheduleId(bookingRequest.getScreenScheduleCode());
        bookingEntity.setBookingStatus(bookingRequest.getBookingStatus());
        BookingDetails savedBookingDetails = bookingDetailsRepository.save(bookingEntity);
        BookingResponse response = new BookingResponse();
        response.setBookingId(savedBookingDetails.getId());
        response.setBookingStatus(savedBookingDetails.getBookingStatus().toString());
        return response;
    }

    @Override
    public Optional<BookingResponse> changeBookingStatus(CreateBookingRequest bookingRequest) {
        Optional<BookingDetails> bookingDetailsOptional = bookingDetailsRepository.findById(bookingRequest.getBookingId());
        BookingResponse response = null;
        if(bookingDetailsOptional.isPresent()){
            BookingDetails bookingDetails = bookingDetailsOptional.get();
            bookingDetails.setBookingStatus(bookingRequest.getBookingStatus());
            bookingDetailsRepository.save(bookingDetails);
            response = new BookingResponse(bookingRequest.getBookingId(), bookingDetails.getBookingStatus());
        }
        return Optional.ofNullable(response);
    }

    @Override
    public ResponseEntity<?> cancelBooking(CreateBookingRequest bookingRequest) {
        Optional<BookingDetails> bookingDetails = bookingDetailsRepository.findById(bookingRequest.getBookingId());
        if(bookingDetails.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BookingId not found");
        }
        BookingDetails bookingDetailsEntity = bookingDetails.get();
        bookingDetailsEntity.setBookingStatus(BookingStatus.CANCELLED);
        BookingDetails save = bookingDetailsRepository.save(bookingDetailsEntity);
        return ResponseEntity.ok("Booking Cancelled Successfully");
    }

    @Override
    public ResponseEntity<?> getHistory(String id, Integer cursor, Integer count) {
        return null;
    }

}
