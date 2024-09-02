package com.project.JournalApplication.controllers;

import com.project.JournalApplication.entity.User;
import com.project.JournalApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }
}
