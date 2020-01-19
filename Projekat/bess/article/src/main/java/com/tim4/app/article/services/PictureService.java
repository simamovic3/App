package com.tim4.app.article.services;

import com.tim4.app.article.exceptions.NotFoundException;
import com.tim4.app.article.models.Picture;
import com.tim4.app.article.repositories.ArticleRepository;
import com.tim4.app.article.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class PictureService {

    private final PictureRepository pictureRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository, ArticleRepository articleRepository){
        this.pictureRepository = pictureRepository;
        this.articleRepository = articleRepository;
    }

    public List<Picture> persist(@RequestParam int articleId){
        Picture picture1 = new Picture("Picture1", "https://www.bla.com", articleRepository.getOne(articleId));
        pictureRepository.save(picture1);
        return pictureRepository.findAll();
    }

    public ResponseEntity<?> readPictures(){
        Collection<Picture> pictures = this.pictureRepository.findAll();
        if(pictures.isEmpty())
        {
            return new ResponseEntity(new NotFoundException().DisplayErrorMessage("There is no pictures in the database"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<Picture>>(pictures, HttpStatus.OK);
    }

    public ResponseEntity<?> deletePicture(@RequestParam Integer pictureId) {
        Optional<Picture> picture = this.pictureRepository.findById(pictureId);

        if (!picture.isPresent())
        {
            return new ResponseEntity(new NotFoundException().DisplayErrorMessage("Picture with id: " + pictureId + " was not found."), HttpStatus.NOT_FOUND);
        }
        this.pictureRepository.deleteById(pictureId);
        Collection<Picture> pictures = this.pictureRepository.findAll();
        return new ResponseEntity<Collection<Picture>>(pictures, HttpStatus.OK);
    }

    public ResponseEntity<?> getPictureById(@RequestParam Integer pictureId) {
        Optional<Picture> picture = this.pictureRepository.findById(pictureId);

        if (!picture.isPresent())
        {
            return new ResponseEntity(new NotFoundException().DisplayErrorMessage("Picture with id: " + pictureId + " was not found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Picture>>(picture, HttpStatus.OK);
    }
}
