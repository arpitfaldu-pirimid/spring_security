package com.example.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String adminHash = encoder.encode("admin123");
        String userHash  = encoder.encode("user123");

        System.out.println("Admin hash  : " + adminHash);
        System.out.println("User hash   : " + userHash);
    }
}
