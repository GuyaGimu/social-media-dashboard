package com.guya.Social_Media_Dashboard.models.DTOs;

import java.util.UUID;

public class LikeRequest {

    private UUID userId;
    private UUID postId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }
}
