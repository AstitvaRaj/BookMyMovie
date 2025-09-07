package com.bookmymovie.payments.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "event_category")
public class EventCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    private String categoryName;

    private Boolean activeFlag;

    @OneToMany(mappedBy = "eventCategories", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Events> events = new ArrayList<>();



}
