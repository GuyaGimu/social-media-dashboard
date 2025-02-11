package com.guya.Social_Media_Dashboard.services;

import com.guya.Social_Media_Dashboard.models.Comment;
import com.guya.Social_Media_Dashboard.models.Post;
import com.guya.Social_Media_Dashboard.models.User;
import com.guya.Social_Media_Dashboard.repositories.CommentDAO;
import com.guya.Social_Media_Dashboard.repositories.PostDAO;
import com.guya.Social_Media_Dashboard.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentDAO commentDAO;;

    private final PostDAO postDAO;

    private final UserDAO userDAO;

    @Autowired
    public CommentService(CommentDAO commentDAO, PostDAO postDAO, UserDAO userDAO) {
        this.commentDAO = commentDAO;
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }

    public Comment addComment(UUID userId, UUID postId, String content) {
        User user = userDAO.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Post post = postDAO.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(content);
        comment.setCreateAt(LocalDateTime.now());

        return commentDAO.save(comment);
    }
}
