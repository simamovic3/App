package com.tim4.app.uploadpicture.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UploadException extends RuntimeException {

    public UploadException(){}

    public String DisplayErrorMessage(String error){
        return error;
    }

    public ResponseEntity<?> PictureNotFound(Integer pictureId){
        String errorMessage = "Picture with id: " + pictureId + " was not found";
        return new ResponseEntity<String>(errorMessage,HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<?> ExistingUrl(String url){
        String errorMessage = "Picture with url:" + url + " already exists";
        return new ResponseEntity<String>(errorMessage,HttpStatus.CONFLICT);
    }
    public ResponseEntity<?> NoPicturesInDB(){
        String errorMessage = "There is no uploads in the database";
        return new ResponseEntity<String>(errorMessage,HttpStatus.NO_CONTENT);
    }
}