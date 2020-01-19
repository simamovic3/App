package com.tim4.app.article.controllers;

import com.tim4.app.article.exceptions.NotFoundException;
import com.tim4.app.article.models.Article;
import com.tim4.app.article.models.Category;
import com.tim4.app.article.models.User;
import com.tim4.app.article.repositories.ArticleRepository;
import com.tim4.app.article.repositories.CategoryRepository;
import com.tim4.app.article.repositories.UserRepository;
import com.tim4.app.article.services.ArticleService;
import com.tim4.app.article.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@Produces(MediaType.APPLICATION_JSON)
@RestController
@RequestMapping(value = "/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public ArticleController(ArticleService articleService, CategoryRepository categoryRepository, UserRepository userRepository){
        this.articleService = articleService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/addArticle")
    public ResponseEntity<?> addArticle(@RequestBody Article article){
        return  articleService.addArticle(article);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public ResponseEntity<?> readArticles(){
       return  articleService.readArticles();
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.DELETE}, value = "/deleteById")
    public ResponseEntity<?> deleteArticle(@RequestParam Integer articleId) {
        return articleService.deleteArticle(articleId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public ResponseEntity<?> getArticleById(@RequestParam Integer articleId) {
       return articleService.getArticleById(articleId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByCategory")
    public ResponseEntity<?> getArticlesByCategory(@RequestParam String category) {
        return articleService.getArticleByCategory(category);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByUsername/{username}")
    public ResponseEntity<?> getArticlesByUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return articleService.getArticleByUser(user);
    }
}
