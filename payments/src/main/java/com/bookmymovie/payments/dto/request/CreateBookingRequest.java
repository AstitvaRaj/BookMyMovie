package com.bookmymovie.payments.dto.request;

import com.bookmymovie.payments.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CreateBookingRequest {

    UUID bookingId;
    String seatCode;
    UUID screenScheduleCode;
    BookingStatus bookingStatus = BookingStatus.PENDING;
    CreateOrderRequest orderRequest;

}
