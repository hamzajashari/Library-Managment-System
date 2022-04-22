package com.mnb.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LibraryCartException extends RuntimeException{

    public LibraryCartException(Long id) {
        super(String.format("Shopping cart with id: %d was not found", id));
    }
}
