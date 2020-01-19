package com.tim4.app.article.controllers;

import com.tim4.app.article.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@CrossOrigin(origins = "*")
@Produces(MediaType.APPLICATION_JSON)
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;

    }
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public ResponseEntity<?> readCategories(){
        return  categoryService.readCategories();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getByTitle/{title}")
    @ResponseBody
    public ResponseEntity<?> getCategoryByTitle(@PathVariable String title) {
        return categoryService.getCategoryByTitle(title);
    }




}
