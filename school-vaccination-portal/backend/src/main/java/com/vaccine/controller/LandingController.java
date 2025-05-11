package com.vaccine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController {
    @GetMapping("/")
    public String home() {
        return "Welcome to the School Vaccination Portal. <a href='/oauth2/authorization/google'>Login with Google</a>";
    }
}
