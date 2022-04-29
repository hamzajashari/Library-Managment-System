package com.mnb.entity.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class BookAlreadyInLibraryCartException extends RuntimeException{
    public BookAlreadyInLibraryCartException(Long id, String username){
        super(String.format("Book with id: %d already exists in shopping cart for user with username %s", id,username));
    }
}
