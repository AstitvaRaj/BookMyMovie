package com.bookmymovie.payments.service.serviceimpl;

import com.bookmymovie.payments.dto.request.CreateBookingRequest;
import com.bookmymovie.payments.dto.request.CreateOrderRequest;
import com.bookmymovie.payments.dto.request.VerifyOrderRequest;
import com.bookmymovie.payments.dto.response.BookingResponse;
import com.bookmymovie.payments.enums.BookingStatus;
import com.bookmymovie.payments.service.BookingService;
import com.bookmymovie.payments.service.Payment;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RazorpayPaymentsImpl implements Payment {

    @Autowired
    BookingService bookingService;

    @Autowired
    RazorpayClient razorpayClient;

    @Autowired(required = false)
    RedisTemplate<String, String> redisTemplate;

    @Value("${razorpay.client.test.key-secret}")
    private String keySecret;


    @Override
    public ResponseEntity<?> createOrder(CreateBookingRequest bookingRequest, String username) {
        try {
            BookingResponse booking = bookingService.createBooking(bookingRequest, UUID.fromString(username));
            if (booking.getBookingId() != null) {
//                redisTemplate.
//                redisTemplate.opsForList().rightPush("booking" + bookingRequest.getScreenScheduleCode().toString(), bookingRequest.getSeatCode());
            }
            CreateOrderRequest orderRequest = bookingRequest.getOrderRequest();
            JSONObject orderJsonRequest = new JSONObject();
            orderJsonRequest.put("amount", orderRequest.getAmount());
            orderJsonRequest.put("currency", orderRequest.getCurrency().toString());
            orderJsonRequest.put("receipt", orderRequest.getReceipt());
            orderJsonRequest.put("notes", "BookingRef#" + booking.getBookingId().toString());
            Order order = razorpayClient.orders.create(orderJsonRequest);
            return ResponseEntity.ok(order.toJson().toMap());
        } catch (RazorpayException razorpayException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(razorpayException.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> verifyOrder(VerifyOrderRequest verifyRequest, String username) {
        try {
            JSONObject attributes = new JSONObject();
            attributes.put("razorpay_order_id", verifyRequest.getRazorpayOrderId());
            attributes.put("razorpay_payment_id", verifyRequest.getRazorpayPaymentId());
            attributes.put("razorpay_signature", verifyRequest.getRazorpaySignature());
            boolean isValid = Utils.verifyPaymentSignature(attributes, keySecret);
            if (isValid) {
                CreateBookingRequest createBookingRequest = new CreateBookingRequest();
                createBookingRequest.setBookingId(UUID.fromString(verifyRequest.getBookingId()));
                createBookingRequest.setBookingStatus(BookingStatus.CONFIRMED);
                Optional<BookingResponse> bookingResponse = bookingService.changeBookingStatus(createBookingRequest);
                if(bookingResponse.isEmpty()){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Booking Id");
                }
                return ResponseEntity.ok("Payment Verified Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Payment Signature");
            }
        } catch (RazorpayException razorpayException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(razorpayException.getMessage());
        }
    }

}
