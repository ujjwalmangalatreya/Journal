package com.project.JournalApplication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/health-checkup")
    public String healthCheck(){
        return "Health Looks Good !!!!";
    }
}
