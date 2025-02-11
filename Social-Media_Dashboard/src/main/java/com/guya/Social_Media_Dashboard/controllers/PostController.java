package com.guya.Social_Media_Dashboard.controllers;

import com.guya.Social_Media_Dashboard.models.DTOs.*;
import com.guya.Social_Media_Dashboard.models.Post;
import com.guya.Social_Media_Dashboard.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody CreatePostRequest createPostRequest) {
        //Extract data from the request body
        UUID userId = createPostRequest.getUserId();
        String content = createPostRequest.getContent();
        String mediaUrl = createPostRequest.getMediaUrl();

        //call the service to create a new post
        Post post = postService.createPost(userId, content, mediaUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("edit/{postId}")
    public ResponseEntity<Post> editPost(@PathVariable UUID postId, @RequestBody EditPostRequest editPostRequest ) {
        //Extract data from the request body
        UUID userId = editPostRequest.getUserId();
        String content = editPostRequest.getNewContent();

        //call the service to edit the post
        Post updatedPost = postService.editPost(userId, postId, content);

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedPost);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePost(@RequestBody DeletePostRequest request) {
        return ResponseEntity.ok(postService.deletePost(request.getPostId(),request.getUserId()));
    }

    @GetMapping("/feed")
    private ResponseEntity<List<PostDTO>> getFeed(@RequestBody FeedRequest feedRequest) {

        UUID userId = feedRequest.getUserId();
        List<PostDTO> feed = postService.getFeedsPosts(userId);
        return ResponseEntity.ok(feed);
    }
}
