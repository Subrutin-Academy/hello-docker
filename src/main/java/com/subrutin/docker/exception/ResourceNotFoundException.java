package com.subrutin.docker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class ResourceNotFoundException extends  RuntimeException {
    
    public ResourceNotFoundException(String message){
        super(message);
    }
}