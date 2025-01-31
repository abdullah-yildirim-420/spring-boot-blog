package com.blog.blogspot.controller;

import com.blog.blogspot.dto.LoginRequestDTO;
import com.blog.blogspot.dto.RegisterRequestDTO;
import com.blog.blogspot.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){
        authService.register(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        String token = authService.login(loginRequestDTO);
        return ResponseEntity.ok(Collections.singletonMap("token",token));
    }
}
