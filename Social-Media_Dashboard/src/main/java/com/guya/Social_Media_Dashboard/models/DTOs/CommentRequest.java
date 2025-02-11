package com.guya.Social_Media_Dashboard.models.DTOs;

import java.util.UUID;

public class CommentRequest {

    private UUID userId;
    private UUID postId;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
