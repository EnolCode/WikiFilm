package com.wikiFilm.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = true, length = 45)
    private String surname;

    @Column(nullable = true, length = 45)
    private String country;

    @Column(nullable = true)
    private Integer birthYear;

    @Column(nullable = true)
    private Float rating;

    @JsonIgnore
    @OneToMany(mappedBy="author", cascade=CascadeType.ALL)
    private List<Film> films;
}
