package com.tim4.app.uploadpicture.repositories;

import com.tim4.app.uploadpicture.models.UploadPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UploadPictureRepository extends JpaRepository<UploadPicture,Integer>, CustomUploadPictureRepository {

}

