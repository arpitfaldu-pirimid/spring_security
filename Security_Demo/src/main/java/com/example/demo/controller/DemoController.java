package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {

        String username = authentication.getName();

        if ("admin".equals(username)) {
            return "Hello Admin";
        }

        return "Hello User";
    }
}
