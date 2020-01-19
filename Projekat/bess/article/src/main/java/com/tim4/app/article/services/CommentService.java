package com.tim4.app.article.services;

import com.tim4.app.article.exceptions.NotFoundException;
import com.tim4.app.article.models.Comment;
import com.tim4.app.article.repositories.ArticleRepository;
import com.tim4.app.article.repositories.CommentRepository;
import com.tim4.app.article.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, ArticleRepository articleRepository){
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    public List<Comment> persist(@RequestParam int userId, @RequestParam int articleId) {
        Comment comment1 = new Comment("Danas je lijep dan!", articleRepository.getOne(articleId), userRepository.getOne(userId));
        commentRepository.save(comment1);
        return commentRepository.findAll();
    }

    public ResponseEntity<?> readComments(){
        Collection<Comment> comments = this.commentRepository.findAll();
        if(comments.isEmpty())
        {
            return new ResponseEntity(new NotFoundException().DisplayErrorMessage("There is no comments in the database"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<Comment>>(comments, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteComment(@RequestParam Integer commentId) {
        Optional<Comment> comment = this.commentRepository.findById(commentId);

        if (!comment.isPresent())
        {
            return new ResponseEntity(new NotFoundException().DisplayErrorMessage("Comment with id: " + commentId + " was not found."), HttpStatus.NOT_FOUND);
        }
        this.commentRepository.deleteById(commentId);
        Collection<Comment> comments = this.commentRepository.findAll();
        return new ResponseEntity<Collection<Comment>>(comments, HttpStatus.OK);
    }

    public ResponseEntity<?> getCommentByID(@RequestParam Integer commentId) {
        Optional<Comment> comment = this.commentRepository.findById(commentId);

        if (!comment.isPresent())
        {
            return new ResponseEntity(new NotFoundException().DisplayErrorMessage("Comment with id: " + commentId + " was not found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Comment>>(comment, HttpStatus.OK);
    }
}
