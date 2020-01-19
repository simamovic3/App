package com.tim4.app.uploadpicture.models;

import org.apache.commons.validator.routines.UrlValidator;
import org.hibernate.validator.constraints.URL;
import org.apache.commons.validator.routines.*; // Import routines package!
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "upload_picture")
public class UploadPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "picture_generator")
    @SequenceGenerator(name="picture_generator", sequenceName = "picture_seq", allocationSize=1)
    @Column(name = "picture_id")
    private Integer pictureId;

    @NotEmpty(message = "Url cannot be empty")
    @URL(message = "Please input valid URL")
    @Column(name = "picture_url")
    private String pictureUrl;

    public UploadPicture() {}

    public UploadPicture(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
