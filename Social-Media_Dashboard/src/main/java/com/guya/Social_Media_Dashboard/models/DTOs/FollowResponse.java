package com.guya.Social_Media_Dashboard.models.DTOs;

import java.util.UUID;

public class FollowResponse {
    private UUID userId;
    private UUID followedUserId;
    private String message;

    public FollowResponse(UUID userId, UUID followedUserId, String message) {
        this.userId = userId;
        this.followedUserId = followedUserId;
        this.message = message;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getFollowedUserId() {
        return followedUserId;
    }

    public void setFollowedUserId(UUID followedUserId) {
        this.followedUserId = followedUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
