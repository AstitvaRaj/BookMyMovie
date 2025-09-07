package com.bookmymovie.events.entities;

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
@Table(name = "events")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID eventsId;

    private String eventName;

    private String status;

    private String description;

    private Long eventCategory;

    private UUID eventOwner;

    private Date releaseDate;

    private Date removeDate;

    private Long duration;

    @OneToMany(mappedBy = "events", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScreenSchedule> screenSchedules = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eventCategory", insertable = false , updatable = false)
    private EventCategory eventCategories;

}