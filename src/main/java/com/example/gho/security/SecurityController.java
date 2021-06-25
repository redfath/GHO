package com.example.gho.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
public class SecurityController {

    @GetMapping("/403")

    public String accessDenied() {
        return "403";
    }
}
