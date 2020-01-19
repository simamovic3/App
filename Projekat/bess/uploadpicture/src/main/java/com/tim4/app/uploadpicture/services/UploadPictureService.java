package com.tim4.app.uploadpicture.services;

import com.tim4.app.uploadpicture.exceptions.UploadException;
import com.tim4.app.uploadpicture.models.UploadPicture;
import com.tim4.app.uploadpicture.repositories.UploadPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Component
public class UploadPictureService {

    private final UploadPictureRepository uploadPictureRepository;

    @Autowired
    public UploadPictureService(UploadPictureRepository uploadPictureRepository) {
        this.uploadPictureRepository = uploadPictureRepository;
    }

    public ResponseEntity<?> readUploads(){
        Collection<UploadPicture> uploads = this.uploadPictureRepository.findAll();
        if(uploads.isEmpty())
        {
            return new UploadException().NoPicturesInDB();
        }
        return new ResponseEntity<Collection<UploadPicture>>(uploads, HttpStatus.OK);
    }

    public ResponseEntity<?> retrieveUpload(@PathVariable Integer pictureId) {
        Optional<UploadPicture> upload = this.uploadPictureRepository.findById(pictureId);

        if (!upload.isPresent())
        {
            return new UploadException().PictureNotFound(pictureId);
        }
        return new ResponseEntity<Optional<UploadPicture>>(upload, HttpStatus.OK);
    }

    public ResponseEntity<?> addPicture(@Valid @RequestBody UploadPicture picture) {

        if(uploadPictureRepository.duplicateUrl(picture.getPictureUrl()))
        {
            return new UploadException().ExistingUrl(picture.getPictureUrl());
        }
        this.uploadPictureRepository.save(picture);
        Collection<UploadPicture>uploads = this.uploadPictureRepository.findAll();
        return new ResponseEntity<Collection<UploadPicture>>(uploads,HttpStatus.OK);
    }

    public ResponseEntity<?> updatePicture(@Valid @PathVariable("pictureId") Integer pictureId, @RequestBody UploadPicture upload) {

        Optional<UploadPicture> uploadFromDatabase = this.uploadPictureRepository.findById(pictureId);

        if (!uploadFromDatabase.isPresent())
        {
            return new UploadException().PictureNotFound(pictureId);
        }
        if(uploadPictureRepository.duplicateUrl(upload.getPictureUrl()))
        {
            return new UploadException().ExistingUrl(upload.getPictureUrl());
        }

        uploadFromDatabase.get().setPictureUrl(upload.getPictureUrl());
        this.uploadPictureRepository.save(uploadFromDatabase.get());
        return new ResponseEntity<Optional<UploadPicture>>(uploadFromDatabase, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUpload(@PathVariable Integer pictureId) {
        Optional<UploadPicture> uploadFromDatabase = this.uploadPictureRepository.findById(pictureId);

        if (!uploadFromDatabase.isPresent())
        {
            return new UploadException().PictureNotFound(pictureId);
        }
        this.uploadPictureRepository.deleteById(pictureId);
        Collection<UploadPicture>uploads = this.uploadPictureRepository.findAll();
        return new ResponseEntity<Collection<UploadPicture>>(uploads,HttpStatus.OK);
    }

    public  ResponseEntity<?> addMocukps(){
        UploadPicture A = new UploadPicture("https://c1.staticflickr.com/2/1520/24330829813_944c817720_b.jpg");
        uploadPictureRepository.save(A);
        UploadPicture AB = new UploadPicture("https://c1.staticflickr.com/1/1520/24330829813_944c817720_b.jpg");
        uploadPictureRepository.save(AB);
        Collection<UploadPicture>uploads = this.uploadPictureRepository.findAll();
        return new ResponseEntity<Collection<UploadPicture>>(uploads,HttpStatus.OK);
    }
}
