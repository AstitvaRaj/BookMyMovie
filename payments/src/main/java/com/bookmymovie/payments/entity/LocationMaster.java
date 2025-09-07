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
@Table(name = "location_master")
public class LocationMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "locationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VenueEntity> venueEntities = new ArrayList<>();

}