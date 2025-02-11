package com.guya.Social_Media_Dashboard.controllers;

import com.guya.Social_Media_Dashboard.models.DTOs.LikeRequest;
import com.guya.Social_Media_Dashboard.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/like")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/likePost")
    public ResponseEntity<String> likePost(@RequestBody LikeRequest likeRequest) {
        UUID userId = likeRequest.getUserId();
        UUID postId = likeRequest.getPostId();

        return ResponseEntity.ok(likeService.likePost(userId, postId));
    }

}
