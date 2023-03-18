package com.wikiFilm.models;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private float rating;

    @Column(nullable = true)
    private String description;
}
