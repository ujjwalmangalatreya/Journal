package com.project.JournalApplication.controllers;

import com.project.JournalApplication.entity.User;
import com.project.JournalApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-checkup")
    public String healthCheck(){
        return "Health Looks Good !!!!";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        userService.saveNewUser(user);
        return new ResponseEntity<>("{status : 'Success', message : 'User Successfully created'}",HttpStatus.CREATED);
    }
}
