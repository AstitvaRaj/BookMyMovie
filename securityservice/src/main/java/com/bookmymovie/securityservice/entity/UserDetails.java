package com.bookmymovie.securityservice.entity;

import com.bookmymovie.securityservice.dto.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_details_id", nullable = false)
    private UUID id;

    private String firstName;

    private String lastName;

    private String phoneNo;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    private Integer countryCode;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Users users;

}
