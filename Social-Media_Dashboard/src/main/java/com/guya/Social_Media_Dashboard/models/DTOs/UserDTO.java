package com.guya.Social_Media_Dashboard.models.DTOs;

import java.util.UUID;

public class UserDTO {

    private UUID userId;
    private String username;
    private String name;
    private String profilePicture;

    public UserDTO(UUID userId, String username, String name, String profilePicture) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.profilePicture = profilePicture;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
