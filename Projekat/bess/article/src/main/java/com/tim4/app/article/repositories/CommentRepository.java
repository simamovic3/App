package com.tim4.app.article.repositories;

import com.tim4.app.article.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}