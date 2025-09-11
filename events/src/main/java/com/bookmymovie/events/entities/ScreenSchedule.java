package com.bookmymovie.events.entities;

import com.bookmymovie.events.dto.enums.EventScheduledStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "screen_schedule")
public class ScreenSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private Date startTime;

    private Date endTime;

    @Enumerated(EnumType.STRING)
    private EventScheduledStatus status ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen")
    private Screens screens;

    @ManyToOne
    @JoinColumn(name = "event")
    private EventLanguage events;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<SeatPricingEntity> seatPricing = new ArrayList<>();

}