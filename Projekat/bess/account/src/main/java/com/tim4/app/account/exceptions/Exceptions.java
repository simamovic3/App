package com.tim4.app.account.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Exceptions extends RuntimeException{

    public Exceptions(){}

    public String DisplayErrorMessage(String error){
        return error;
    }

    public ResponseEntity<?> NoUsersInDatabase(){
        String errorMessage = "There is no users in the database";
        return new ResponseEntity<String>(errorMessage, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> NoAuthsInDatabase(){
        String errorMessage = "There is no auths in the database";
        return new ResponseEntity<String>(errorMessage, HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<?> EmptyInput(){
        String errorMessage = "Input cannot be empty";
        return new ResponseEntity<String>(errorMessage,HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> UserNotFound(String username){
        String errorMessage = "User with username: " + username + " was not found";
        return new ResponseEntity<String>(errorMessage,HttpStatus.NOT_FOUND);
    }

}
