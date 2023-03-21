package com.wikiFilm.exception;

public class AuthorNotFoundException  extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
