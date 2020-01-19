package com.tim4.app.article.repositories;

import com.tim4.app.article.models.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository  extends JpaRepository<Picture, Integer> {
}
