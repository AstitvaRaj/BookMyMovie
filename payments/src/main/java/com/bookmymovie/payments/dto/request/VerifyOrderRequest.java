package com.bookmymovie.payments.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VerifyOrderRequest {
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;
    private String bookingId;
}
