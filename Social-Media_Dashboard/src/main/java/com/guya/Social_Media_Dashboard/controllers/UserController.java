package com.guya.Social_Media_Dashboard.controllers;

import com.guya.Social_Media_Dashboard.models.DTOs.FollowResponse;
import com.guya.Social_Media_Dashboard.models.DTOs.UpdateProfileRequest;
import com.guya.Social_Media_Dashboard.models.User;
import com.guya.Social_Media_Dashboard.services.FollowerService;
import com.guya.Social_Media_Dashboard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final FollowerService followerService;

    @Autowired
    public UserController(UserService userService, FollowerService followerService) {
        this.userService = userService;
        this.followerService = followerService;
    }

    //Insert a user
    @PostMapping("/register")
    public ResponseEntity<User> insertUser(@RequestBody User user){
        return ResponseEntity.ok(userService.insertUser(user));
    }

    //Update user profile
    @PutMapping("/updateuserprofile/{userId}")
    public ResponseEntity<User> updateUserProfile(@PathVariable UUID userId, @RequestBody UpdateProfileRequest updatedProfileRequest){

        //Extract the new username and profile picture from the request
        String username = updatedProfileRequest.getUsername();
        String newProfilePicture = updatedProfileRequest.getProfilePicture();

        //update the user profile
        User updatedUser = userService.updateUserProfile(userId, username, newProfilePicture);

        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/{userId}/follow/{followedUserId}")
    public ResponseEntity<FollowResponse> followOrUnfollowUser(
            @PathVariable UUID userId,
            @PathVariable UUID followedUserId){
        String message = followerService.followOrUnfollow(userId, followedUserId);
        return ResponseEntity.ok(new FollowResponse(userId, followedUserId, message));
    }
}
