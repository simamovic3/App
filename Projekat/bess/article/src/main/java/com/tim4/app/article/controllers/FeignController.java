package com.tim4.app.article.controllers;

import com.tim4.app.article.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RefreshScope
@RestController
public class FeignController {

    @Autowired
    public FeignControllerInterface feignControllerInterface;

    @RequestMapping(value = "/feign", params = "id")
    public Optional<User> findOne(@RequestParam("id") Integer id) {
        return feignControllerInterface.findById(id);
    }

    @RequestMapping("/feign/accountUseri")
    public List<User> readUsers(){
        return feignControllerInterface.findAll();
    }

}
