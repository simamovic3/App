package com.tim4.app.article.controllers;

import com.tim4.app.article.exceptions.NotFoundException;
import com.tim4.app.article.models.User;
import com.tim4.app.article.repositories.UserRepository;
import com.tim4.app.article.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public ResponseEntity<?> readUsers(){
        return userService.readUsers();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getByUsername/{username}")
    @ResponseBody
    public ResponseEntity<?> getCategoryByTitle(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
