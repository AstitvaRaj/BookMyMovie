package com.bookmymovie.payments.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "venue")
public class VenueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private UUID city;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private LocationMaster locationMaster;

    @OneToMany(mappedBy = "venueEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Screens> screens = new ArrayList<>();

}