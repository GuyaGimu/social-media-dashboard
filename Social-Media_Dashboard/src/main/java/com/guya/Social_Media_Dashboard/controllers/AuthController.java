package com.guya.Social_Media_Dashboard.controllers;

import com.guya.Social_Media_Dashboard.models.DTOs.AuthResponse;
import com.guya.Social_Media_Dashboard.models.DTOs.LoginRequest;
import com.guya.Social_Media_Dashboard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest){
        AuthResponse authResponse = userService.verify(loginRequest);
        return ResponseEntity.ok(authResponse);
    }

}
