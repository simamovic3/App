package com.tim4.app.article.services;

import com.tim4.app.article.exceptions.NotFoundException;
import com.tim4.app.article.models.Article;
import com.tim4.app.article.models.User;
import com.tim4.app.article.repositories.ArticleRepository;
import com.tim4.app.article.repositories.CategoryRepository;
import com.tim4.app.article.repositories.UserRepository;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Optional;

@Component
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final RabbitTemplate rabbitTemplate;
    private final Exchange exchange;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, CategoryRepository categoryRepository, CategoryService categoryService, UserRepository userRepository, UserService userService, RabbitTemplate rabbitTemplate, Exchange exchange){
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.exchange = exchange;
        this.rabbitTemplate = rabbitTemplate;
    }

    public ResponseEntity<?> readArticles(){
        categoryService.saveFeignUsers();
        Collection<Article> articles = this.articleRepository.findAll();
        if(articles.isEmpty())
        {
            return new ResponseEntity(new NotFoundException().DisplayErrorMessage("There is no articles in the database"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<Article>>(articles, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteArticle(@RequestParam Integer articleId) {
        Optional<Article> article = this.articleRepository.findById(articleId);

        if (!article.isPresent())
        {
            return new ResponseEntity(new NotFoundException().DisplayErrorMessage("Article with id: " + articleId + " was not found."), HttpStatus.NOT_FOUND);
        }
        this.articleRepository.deleteById(articleId);
        Collection<Article> articles = this.articleRepository.findAll();
        return new ResponseEntity<Collection<Article>>(articles, HttpStatus.OK);
    }

    public ResponseEntity<?> getArticleById(@RequestParam Integer articleId) {
        Optional<Article> article = this.articleRepository.findById(articleId);

        if (!article.isPresent())
        {
            return new ResponseEntity(new NotFoundException().DisplayErrorMessage("Article with id: " + articleId + " was not found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Article>>(article, HttpStatus.OK);
    }

    public ResponseEntity<?> getArticleByCategory(String category) {
        Collection<Article>listOfArticles = articleRepository.findByCategory(category);

        if(!listOfArticles.isEmpty())
          return new ResponseEntity<Collection<Article>>(articleRepository.findByCategory(category), HttpStatus.OK);

        return new ResponseEntity(new NotFoundException().DisplayErrorMessage("There are no articles with category: "+ category), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getArticleByUser(User user) {
        Collection<Article>listOfArticles = articleRepository.findByUser(user);

        if(!listOfArticles.isEmpty())
            return new ResponseEntity<Collection<Article>>(listOfArticles, HttpStatus.OK);

        return new ResponseEntity(new NotFoundException().DisplayErrorMessage("There are no articles with username: "+ user), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> addArticle(Article article) {
        articleRepository.save(article);
        return new ResponseEntity<Article>(article, HttpStatus.OK);
    }
}
