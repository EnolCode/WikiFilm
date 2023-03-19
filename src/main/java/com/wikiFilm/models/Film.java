package com.wikiFilm.models;

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

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = true)
    private Integer releaseYear;

    @Column(nullable = false)
    private float rating;

    @Column(nullable = true)
    private String description;
}
