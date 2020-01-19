package com.tim4.app.article.services;

import com.tim4.app.article.controllers.FeignController;
import com.tim4.app.article.exceptions.NotFoundException;
import com.tim4.app.article.models.User;
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
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> readUsers(){
        Collection<User> users = this.userRepository.findAll();
        if(users.isEmpty())
        {
            return new ResponseEntity(new NotFoundException().DisplayErrorMessage("There is no users in the database"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
    }
    public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
        User a = this.userRepository.findByUsername(username);
        return new ResponseEntity<User>(a, HttpStatus.OK);
    }
}
