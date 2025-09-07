package com.bookmymovie.events.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
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

    private UUID screen;

    private UUID event;

    private Date startTime;

    private Date endTime;

    private String status ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event", updatable = false, insertable = false)
    private Events events;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen", updatable = false, insertable = false)
    private Screens screens;

}