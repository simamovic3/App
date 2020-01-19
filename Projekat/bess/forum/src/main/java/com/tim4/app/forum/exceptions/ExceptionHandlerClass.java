package com.tim4.app.forum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionHandlerClass extends RuntimeException {

    public ExceptionHandlerClass(){}

    public String DisplayErrorMessage(String error){
        return error;
    }
}