package com.bookmymovie.events.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "seat_pricing")
public class SeatPricingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "seat_category_id", nullable = false)
    private UUID seatCategoryId;

    @Column(name = "pricing", nullable = false)
    private Double pricing = 0.0;

    @Column(name = "active_flag")
    private Boolean activeFlag = true;

    @Column(name = "created_date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date createdDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "screen_schedule")
    private ScreenSchedule screenSchedule;

}