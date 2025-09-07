package com.bookmymovie.payments.dto.response;

import com.bookmymovie.payments.enums.BookingStatus;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingResponse {

    @Getter
    @Setter
    UUID bookingId;

    BookingStatus bookingStatus;

    public String getBookingStatus() {
        return bookingStatus.toString();
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = BookingStatus.valueOf(bookingStatus);
    }
}
