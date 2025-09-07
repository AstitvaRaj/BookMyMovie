package com.bookmymovie.payments.entity;

import com.bookmymovie.payments.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "booking_details")
public class BookingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "user")
    private UUID userId;

    @Column(name = "schedule_id")
    private UUID scheduleId;

    @Column(name = "seat")
    private String seatCode;

    private Date bookingDate = new Date();

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "screen_schedule_id", insertable = false, updatable = false)
    private ScreenSchedule screenSchedule;

}