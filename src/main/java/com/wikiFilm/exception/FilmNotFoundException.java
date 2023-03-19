package com.wikiFilm.exception;

public class FilmNotFoundException  extends RuntimeException {
    public FilmNotFoundException(String message) {
        super(message);
    }
}
