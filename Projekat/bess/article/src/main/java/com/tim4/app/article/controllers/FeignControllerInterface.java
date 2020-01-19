package com.tim4.app.article.controllers;


import com.tim4.app.article.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.tim4.app.article.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="account" )//Service Id of account service
public interface FeignControllerInterface {

    @RequestMapping(value = "/users/get", params="id")
    public Optional<User> findById(@RequestParam("id") Integer id);

    @RequestMapping("/users/all/feign")
    public List<User> findAll();



}