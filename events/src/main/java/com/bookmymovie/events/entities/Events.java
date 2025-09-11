package com.bookmymovie.events.entities;

import com.bookmymovie.events.dto.enums.EventStatus;
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

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    private String description;

    private Long eventCategory;

    private UUID eventAdmin;

    private Date releaseDate;

    private Long duration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eventCategory", insertable = false , updatable = false)
    private EventCategory eventCategories;

    @OneToMany(mappedBy = "events", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventLanguage> eventLanguages = new ArrayList<>();

}