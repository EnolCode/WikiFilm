package com.wikiFilm.models;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    private Integer rating = 50;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_genre", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private List<Genre> genres;
    
    @ManyToOne(fetch = FetchType.EAGER)    
    @JoinColumn(name= "author_id" , nullable = true, referencedColumnName = "id")
    Author author;

    @JsonIgnore
    @ManyToMany(mappedBy = "films")
    Set<User> users;
}
