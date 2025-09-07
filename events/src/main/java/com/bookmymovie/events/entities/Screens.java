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
@Table(name = "screens")
public class Screens {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String screen_name;

    private UUID venue;

    private String status;

    private Date createdDate = new Date();

    private Integer screen_type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private VenueEntity venueEntity;

    @OneToMany(mappedBy = "screens", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScreenSchedule> screenSchedules = new ArrayList<>();

}