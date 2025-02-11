package com.guya.Social_Media_Dashboard.services;

import com.guya.Social_Media_Dashboard.models.DTOs.PostDTO;
import com.guya.Social_Media_Dashboard.models.DTOs.UserDTO;
import com.guya.Social_Media_Dashboard.models.Follower;
import com.guya.Social_Media_Dashboard.models.Post;
import com.guya.Social_Media_Dashboard.models.User;
import com.guya.Social_Media_Dashboard.repositories.PostDAO;
import com.guya.Social_Media_Dashboard.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostDAO postDAO;
    private  UserDAO userDAO;

    @Autowired
    public PostService(PostDAO postDAO, UserDAO userDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }

    public Post createPost(UUID userId, String content,String mediaUrl) {

        if (content == null || content.trim().isBlank()) {
            throw new IllegalArgumentException("Content must not be empty");
        }
        User user = userDAO.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with  the ID " + userId));

        //Create a new post object
        Post post = new Post();
        post.setUser(user);
        post.setContent(content);
        post.setMediaUrl(mediaUrl);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        return postDAO.save(post);
    }

    public Post editPost(UUID userId, UUID postId, String newContent) {
        if (newContent == null || newContent.trim().isBlank()) {
            throw new IllegalArgumentException("Content must not be empty");
        }
        Post post = postDAO.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with the ID " + postId));
        if (!post.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("User is not the owner of the post");
        }
        post.setContent(newContent);
        post.setUpdatedAt(LocalDateTime.now());

        return postDAO.save(post);
    }

    public String deletePost(UUID postId, UUID userId) {
        Post post = postDAO.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with the ID " + postId));

        if (!post.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("User is not the owner of the post");
        }

        postDAO.delete(post);

        return "Post deleted successfully";
    }

    public List<PostDTO> getFeedsPosts(UUID userId) {
        User user = userDAO.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with the ID " + userId));

        List<User> followedUsers= user.getFollowing().stream()
                .map(Follower::getFollowedUser)
                .collect(Collectors.toList());

        // Log the followed users for debugging
        followedUsers.forEach(followedUser -> {
            System.out.println("Followed User: " + followedUser.getUsername());
        });

        List<Post> posts = postDAO.findByUserInOrderByCreatedAtDesc(followedUsers);

        return posts.stream().map(post -> {
            UserDTO userDTO = new UserDTO(
                    post.getUser().getUserId(),
                    post.getUser().getUsername(),
                    post.getUser().getFullName(),
                    post.getUser().getProfilePicture()
            );
            return new PostDTO(
                    post.getPostId(),
                    post.getContent(),
                    post.getMediaUrl(),
                    post.getCreatedAt(),
                    post.getUpdatedAt(),
                    userDTO
            );
        }).collect(Collectors.toList());
    }

}
