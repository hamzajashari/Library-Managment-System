package com.mnb.entity.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LibraryCartNotFoundException  extends RuntimeException{

    public LibraryCartNotFoundException(Long id){
        super(String.format("Library cart with id: %d was not found",id));
    }
}
