package com.guya.Social_Media_Dashboard.controllers;

import com.guya.Social_Media_Dashboard.models.Comment;
import com.guya.Social_Media_Dashboard.models.DTOs.CommentRequest;
import com.guya.Social_Media_Dashboard.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(@RequestBody CommentRequest commentRequest) {
        Comment comment = commentService.addComment(
                commentRequest.getUserId(),
                commentRequest.getPostId(),
                commentRequest.getContent()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }
}
