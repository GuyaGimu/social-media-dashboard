package com.guya.Social_Media_Dashboard.models.DTOs;

import java.util.UUID;

public class FeedRequest {

    private UUID userId;

    public FeedRequest() {
    }

    public FeedRequest(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
