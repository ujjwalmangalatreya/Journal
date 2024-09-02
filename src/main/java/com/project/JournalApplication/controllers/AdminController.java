package com.project.JournalApplication.controllers;

import com.project.JournalApplication.entity.User;
import com.project.JournalApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> allEntry = userService.getAllEntry();
        if(allEntry !=null && !allEntry.isEmpty()){
            return new ResponseEntity<>(allEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-admin-user")
    public ResponseEntity<?> addAdminUser(@RequestBody User user) {
            userService.saveAdminUser(user);
            return new ResponseEntity<>("Admin User Created Successfully",HttpStatus.CREATED);
    }
}
