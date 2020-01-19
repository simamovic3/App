package com.tim4.app.uploadpicture.controllers;


import com.tim4.app.uploadpicture.models.UploadPicture;
import com.tim4.app.uploadpicture.exceptions.*;
import com.tim4.app.uploadpicture.repositories.UploadPictureRepository;
import com.tim4.app.uploadpicture.services.UploadPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/uploadpicture")
public class UploadPictureController {

    private final UploadPictureService uploadPictureService;

    @Autowired
    public UploadPictureController(UploadPictureService uploadPictureService) {
        this.uploadPictureService = uploadPictureService;
    }

    /*GET ALL UPLOADS*/
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public ResponseEntity<?> readUploads(){
        return uploadPictureService.readUploads();
    }


    /*GET ONE UPLOAD*/
    @RequestMapping(method = RequestMethod.GET, value = "/{pictureId}")
    public ResponseEntity<?> retrieveUpload(@PathVariable Integer pictureId) {
        return uploadPictureService.retrieveUpload(pictureId);
    }

    /*ADD ONE UPLOAD*/
    @RequestMapping(method = RequestMethod.POST, value="/add")
    public ResponseEntity<?> addPicture(@Valid @RequestBody UploadPicture picture) {
        return uploadPictureService.addPicture(picture);
    }

    /*UPDATE UPLOAD*/
    @RequestMapping(method = RequestMethod.PUT, value = "/{pictureId}")
    public ResponseEntity<?> updatePicture(@Valid @PathVariable("pictureId") Integer pictureId, @RequestBody UploadPicture upload) {
        return uploadPictureService.updatePicture(pictureId,upload);
    }

    /*DELETE UPLOAD*/
    @RequestMapping(method = RequestMethod.DELETE, value = "/{pictureId}")
    public ResponseEntity<?> deleteUpload(@PathVariable Integer pictureId) {
       return uploadPictureService.deleteUpload(pictureId);
    }

    /*ADD MOCKUPS*/
    @RequestMapping(method = RequestMethod.GET, value = "/add/addmockups")
    public  ResponseEntity<?> addMocukps(){
        return uploadPictureService.addMocukps();
    }
}
