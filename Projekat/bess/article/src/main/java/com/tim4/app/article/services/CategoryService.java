package com.tim4.app.article.services;

import com.tim4.app.article.controllers.FeignController;
import com.tim4.app.article.exceptions.NotFoundException;
import com.tim4.app.article.models.Article;
import com.tim4.app.article.models.Category;
import com.tim4.app.article.models.User;
import com.tim4.app.article.repositories.ArticleRepository;
import com.tim4.app.article.repositories.CategoryRepository;
import com.tim4.app.article.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.*;

@Component
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final FeignController feignController;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ArticleRepository articleRepository, UserRepository userRepository, FeignController feignController) {

        this.feignController = feignController;
        this.userRepository = userRepository;
        this.userRepository.deleteAll();

        saveFeignUsers();

        this.categoryRepository = categoryRepository;
        this.categoryRepository.deleteAll();

        saveValidCategories();

        this.articleRepository = articleRepository;

    }

    public void saveFeignUsers() {
        for(User user : feignController.readUsers()){
            User user1 = new User(user.getUsername());
            userRepository.save(user1);
        }
    }

    private void saveSomeArt() {

        java.util.Date utilDate = new java.util.Date();

        Article article = new Article("Kitchen renovation in two weeks", "Here are my advices on how to completely renovate kitchen in only two weeks", categoryRepository.findByCattitle("KITCHEN"), userRepository.findByUsername("belma123"), new java.sql.Timestamp(utilDate.getTime()), "https://upload.wikimedia.org/wikipedia/commons/thumb/7/72/Modern_kitchen_gnangarra.JPG/1200px-Modern_kitchen_gnangarra.JPG");
        articleRepository.save(article);

    }


    private void saveValidCategories() {
        Category category1 = new Category("STORIES", "Some renovation stories");
        categoryRepository.save(category1);

        Category category2 = new Category("KITCHEN", "Kitchen renovation advices");
        categoryRepository.save(category2);

        Category category3 = new Category("BATHROOM", "Advices on how to properly design bathrooms");
        categoryRepository.save(category3);

        Category category4 = new Category("BEDROOM", "Some tips for bedroom renovation");
        categoryRepository.save(category4);

        Category category5 = new Category("LIVING ROOM", "Living room decor advices");
        categoryRepository.save(category5);

        Category category6 = new Category("GARDEN", "Advices for designing garden");
        categoryRepository.save(category6);

        Category category7 = new Category("DECOR", "Decor at its finest");
        categoryRepository.save(category7);
    }

    public ResponseEntity<?> readCategories() {
        Collection<Category> categories = this.categoryRepository.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity(new NotFoundException().DisplayErrorMessage("There is no categories in the database"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<Category>>(categories, HttpStatus.OK);
    }

    public ResponseEntity<?> getCategoryByTitle(@RequestParam String cattitle) {
        Category a = this.categoryRepository.findByCattitle(cattitle);
        return new ResponseEntity<Category>(a, HttpStatus.OK);
    }


}
