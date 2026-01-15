package com.example.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

@RestController
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String password) {

        if (userRepository.findByUsername(username).isPresent()) {
            return "Username already exists";
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        System.out.println(user);
        userRepository.save(user);
        return "User registered successfully";
    }
}
