package com.bookmymovie.events.entities;

import com.bookmymovie.events.dto.enums.EventStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "event_language")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventLanguage {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    @Column(name = "event_id", nullable = false)
//    private UUID eventId;
//
//    @Column(name = "language_id", nullable = false)
//    private Integer languageId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Events events;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "language_id")
    private LanguageMaster languageMaster;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<ScreenSchedule> screenSchedules = new ArrayList<>();

}
