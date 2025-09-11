package com.bookmymovie.events.entities;

import com.bookmymovie.events.dto.enums.ScreenLayoutStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "seat_map")
@AllArgsConstructor
@NoArgsConstructor
public class SeatMap {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID screenId;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
//    @Transient
    private ScreenLayout layout;

    @Enumerated(EnumType.STRING)
    private ScreenLayoutStatus status;

}