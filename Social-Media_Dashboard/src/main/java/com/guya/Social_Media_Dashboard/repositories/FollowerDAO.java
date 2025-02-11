package com.guya.Social_Media_Dashboard.repositories;

import com.guya.Social_Media_Dashboard.models.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FollowerDAO extends JpaRepository<Follower, UUID> {
    boolean existsByUser_UserIdAndFollowedUser_UserId(UUID userId, UUID followedUserId);
    void deleteByUser_UserIdAndFollowedUser_UserId(UUID userId, UUID followedUserId);
}
