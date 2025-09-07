package com.bookmymovie.events.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_type")
public class UserTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id", nullable = false)
    private Long id;

    private String userType;

    private Boolean active;

    @OneToMany(mappedBy = "userTypeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Users> users = new ArrayList<>();

}