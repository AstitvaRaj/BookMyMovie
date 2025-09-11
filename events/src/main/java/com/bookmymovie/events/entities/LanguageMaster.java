package com.bookmymovie.events.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "language_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "regional_language", nullable = false)
    private String regionalLanguage;
}
