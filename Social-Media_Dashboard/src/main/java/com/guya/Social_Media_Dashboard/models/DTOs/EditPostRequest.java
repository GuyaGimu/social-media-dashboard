package com.guya.Social_Media_Dashboard.models.DTOs;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class EditPostRequest {

    private UUID userId;

    @NotBlank(message = "Content must not be empty")
    private String newContent;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }
}
