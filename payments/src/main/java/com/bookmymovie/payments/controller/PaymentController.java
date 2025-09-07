package com.bookmymovie.payments.controller;

import com.bookmymovie.payments.dto.request.CreateBookingRequest;
import com.bookmymovie.payments.dto.request.VerifyOrderRequest;
import com.bookmymovie.payments.service.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(value = "*", maxAge = 36000)
public class PaymentController {

    @Autowired
    Payment payment;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody CreateBookingRequest createOrderRequest, @RequestHeader String username) {
        return payment.createOrder(createOrderRequest, username);
    }

    @PostMapping("/verify-order")
    public ResponseEntity<?> verifyOrder(@RequestBody VerifyOrderRequest verifyOrderRequest, @RequestHeader String username) {
        return payment.verifyOrder(verifyOrderRequest, username);
    }

}
