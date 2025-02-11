package com.guya.Social_Media_Dashboard.services;


import com.guya.Social_Media_Dashboard.models.DTOs.AuthResponse;
import com.guya.Social_Media_Dashboard.models.DTOs.LoginRequest;
import com.guya.Social_Media_Dashboard.models.DTOs.UserDTO;
import com.guya.Social_Media_Dashboard.models.User;
import com.guya.Social_Media_Dashboard.repositories.UserDAO;
import com.guya.Social_Media_Dashboard.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private final UserDAO userDAO;

    private final JWTService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private final AuthenticationManager authenticationManager;


    @Autowired
    public UserService(UserDAO userDAO, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.userDAO = userDAO;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public User insertUser(User user){

        // Check if username or email already exists
        if (userDAO.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (userDAO.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        // Set default values
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        if (user.getProfilePicture() == null || user.getProfilePicture().isEmpty()) {
            user.setProfilePicture("http://localhost:4445/images/default-profile-picture.avif");
        }

        // Encode the password
        user.setPassword(encoder.encode(user.getPassword()));
        // Save the user to the database
        return userDAO.save(user);
    }

    public User updateUserProfile(UUID userId, String username, String newProfilePicture) {
        User user = userDAO.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (username != null && !username.isBlank()) {
            user.setUsername(username);
        }
        if (newProfilePicture != null && !newProfilePicture.isBlank()) {
            user.setProfilePicture(newProfilePicture);
        }
        user.setUpdatedAt(LocalDateTime.now());
        return userDAO.save(user);
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    public AuthResponse verify(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if (!authentication.isAuthenticated()){
            throw new IllegalArgumentException("Invalid username or password");
        }
        User user = userDAO.findByUsername(loginRequest.getUsername());

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token, new UserDTO(user.getUserId(), user.getUsername(), user.getFullName(), user.getProfilePicture()));
    }
}
