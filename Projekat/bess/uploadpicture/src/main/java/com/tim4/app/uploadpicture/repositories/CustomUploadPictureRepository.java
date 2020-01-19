package com.tim4.app.uploadpicture.repositories;

public interface CustomUploadPictureRepository {
    boolean duplicateUrl(String pictureUrl);
}
