package com.guya.Social_Media_Dashboard.services;

import com.guya.Social_Media_Dashboard.models.Like;
import com.guya.Social_Media_Dashboard.models.Post;
import com.guya.Social_Media_Dashboard.models.User;
import com.guya.Social_Media_Dashboard.repositories.LikeDAO;
import com.guya.Social_Media_Dashboard.repositories.PostDAO;
import com.guya.Social_Media_Dashboard.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LikeService {

    private LikeDAO likeDAO;

    private PostDAO postDAO;

    private UserDAO userDAO;

    @Autowired
    public LikeService(LikeDAO likeDAO, PostDAO postDAO, UserDAO userDAO) {
        this.likeDAO = likeDAO;
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }

    public String likePost(UUID userId, UUID postId) {

        User user = userDAO.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Post post = postDAO.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));

        if (likeDAO.existsByUserAndPost(user, post)) {
            return "You have already liked this post";
        }

        Like like = new Like();
        like.setPost(post);
        like.setUser(user);
        like.setCreatedAt(LocalDateTime.now());
        likeDAO.save(like);

        return "Post liked successfully";
    }
}
