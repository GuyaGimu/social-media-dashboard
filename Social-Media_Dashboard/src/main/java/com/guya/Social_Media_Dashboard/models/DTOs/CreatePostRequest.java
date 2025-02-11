package com.guya.Social_Media_Dashboard.models.DTOs;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class CreatePostRequest {

    private UUID userId;

    @NotBlank(message = "Content cannot be empty")
    private String content;
    private String mediaUrl;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
