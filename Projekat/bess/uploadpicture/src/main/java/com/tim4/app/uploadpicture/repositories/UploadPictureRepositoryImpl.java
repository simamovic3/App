package com.tim4.app.uploadpicture.repositories;

import com.tim4.app.uploadpicture.models.UploadPicture;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Iterator;

public class UploadPictureRepositoryImpl implements CustomUploadPictureRepository {

    @Autowired
    UploadPictureRepository uploadPictureRepository;

    @Override
    public boolean duplicateUrl(String pictureUrl) {
        Collection<UploadPicture> uploads = this.uploadPictureRepository.findAll();

        for (Iterator<UploadPicture> i = uploads.iterator(); i.hasNext();) {
            if(i.next().getPictureUrl().equals(pictureUrl))
                return true;
        }
        return false;
    }
}