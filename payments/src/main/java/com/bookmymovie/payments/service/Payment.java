package com.bookmymovie.payments.service;

import com.bookmymovie.payments.dto.request.CreateBookingRequest;
import com.bookmymovie.payments.dto.request.VerifyOrderRequest;
import org.springframework.http.ResponseEntity;

public interface Payment {

    ResponseEntity<?> createOrder(CreateBookingRequest orderRequest, String username);
    ResponseEntity<?> verifyOrder(VerifyOrderRequest orderRequest, String username);
}
