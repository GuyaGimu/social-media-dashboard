package com.guya.Social_Media_Dashboard.models;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Entity
@Table(name = "followers")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID followerId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //the user who is following someone

    @ManyToOne
    @JoinColumn(name = "follower_user_id", nullable = false)
    private User followedUser;// the user being followed

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Follower() {
    }

    public Follower(User user, User followedUser, LocalDateTime createdAt) {
        this.user = user;
        this.followedUser = followedUser;
        this.createdAt = createdAt;
    }

    public UUID getFollowerId() {
        return followerId;
    }

    public void setFollowerId(UUID followerId) {
        this.followerId = followerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(User followedUser) {
        this.followedUser = followedUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Follower{" +
                "followerId='" + followerId + '\'' +
                ", user=" + (user != null ? user.getUserId() : "null") +
                ", followerUser=" + (followedUser != null ? followedUser.getUserId() : "null") +
                ", createdAt=" + createdAt +
                '}';
    }
}
