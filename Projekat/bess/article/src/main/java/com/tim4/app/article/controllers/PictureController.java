package com.tim4.app.article.controllers;

import com.tim4.app.article.exceptions.NotFoundException;
import com.tim4.app.article.models.Article;
import com.tim4.app.article.models.Picture;
import com.tim4.app.article.repositories.ArticleRepository;
import com.tim4.app.article.repositories.PictureRepository;
import com.tim4.app.article.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pictures")
public class PictureController {

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService){
        this.pictureService = pictureService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public ResponseEntity<?> readPictures(){
        return pictureService.readPictures();
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.DELETE}, value = "/deleteById")
    public ResponseEntity<?> deletePicture(@RequestParam Integer pictureId) {
        return pictureService.deletePicture(pictureId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public ResponseEntity<?> getPictureById(@RequestParam Integer pictureId) {
        return pictureService.getPictureById(pictureId);
    }
}
