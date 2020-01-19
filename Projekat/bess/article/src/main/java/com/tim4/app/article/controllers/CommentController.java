package com.tim4.app.article.controllers;

import com.tim4.app.article.exceptions.NotFoundException;
import com.tim4.app.article.models.Comment;
import com.tim4.app.article.repositories.ArticleRepository;
import com.tim4.app.article.repositories.CommentRepository;
import com.tim4.app.article.repositories.UserRepository;
import com.tim4.app.article.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping(value = "/load")
    public List<Comment> persist(@RequestParam int userId, @RequestParam int articleId) {
        return commentService.persist(userId, articleId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public ResponseEntity<?> readComments(){
       return commentService.readComments();
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.DELETE}, value = "/deleteById")
    public ResponseEntity<?> deleteComment(@RequestParam Integer commentId) {
        return commentService.deleteComment(commentId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public ResponseEntity<?> getCommentByID(@RequestParam Integer commentId) {
        return commentService.getCommentByID(commentId);
    }
}
