package com.guya.Social_Media_Dashboard.models.DTOs;

import java.util.UUID;

public class PostEngagement {

    private UUID  postId;
    private String content;
    private int likes;
    private int comments;
    private int views;
    private int shares;

    public PostEngagement(UUID postId, String content, int likes, int comments, int views, int shares) {
        this.postId = postId;
        this.content = content;
        this.likes = likes;
        this.comments = comments;
        this.views = views;
        this.shares = shares;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }
}
