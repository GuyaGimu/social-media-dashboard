package com.guya.Social_Media_Dashboard.models;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Entity
@Table(name = "analytics")
public class Analytics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID analyticsId;

    @OneToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private int views =0;
    private int likes =0;
    private int comments =0;
    private int shares =0;

    public Analytics() {
    }

    public Analytics(Post post, int views, int likes, int comments, int shares) {
        this.post = post;
        this.views = views;
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
    }

    public UUID getAnalyticsId() {
        return analyticsId;
    }

    public void setAnalyticsId(UUID analyticsId) {
        this.analyticsId = analyticsId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
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

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    @Override
    public String toString() {
        return "Analytics{" +
                "analyticsId=" + analyticsId +
                ", post=" + (post != null ? post.getPostId() : "null") +
                ", views=" + views +
                ", likes=" + likes +
                ", comments=" + comments +
                ", shares=" + shares +
                '}';
    }
}
