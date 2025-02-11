package com.guya.Social_Media_Dashboard.services;

import com.guya.Social_Media_Dashboard.models.DTOs.UserDTO;
import com.guya.Social_Media_Dashboard.models.Follower;
import com.guya.Social_Media_Dashboard.models.User;
import com.guya.Social_Media_Dashboard.repositories.FollowerDAO;
import com.guya.Social_Media_Dashboard.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FollowerService {

    private final UserDAO  userDAO;
    private  FollowerDAO followerDAO;

    @Autowired
    public FollowerService(UserDAO userDAO, FollowerDAO followerDAO) {
        this.userDAO = userDAO;
        this.followerDAO = followerDAO;
    }

    public String followOrUnfollow(UUID userId, UUID followedUserId) {
        if(userId.equals(followedUserId)){
            throw new IllegalArgumentException("You can't follow yourself");
        }
        Optional<User> userOpt = userDAO.findById(userId);
        Optional<User> followedUserOpt = userDAO.findById(followedUserId);

        if(userOpt.isEmpty() || followedUserOpt.isEmpty()){
            throw new IllegalArgumentException("User not found");
        }

        User user = userOpt.get();
        User followedUser = followedUserOpt.get();

        if( followerDAO.existsByUser_UserIdAndFollowedUser_UserId(userId, followedUserId)) {
            followerDAO.deleteByUser_UserIdAndFollowedUser_UserId(userId, followedUserId);
            return "Successfully Unfollowed  the user";
        } else {
            Follower follower = new Follower();
            follower.setUser(user);
            follower.setFollowedUser(followedUser);
            followerDAO.save(follower);
            return "Successfully Followed the user";
        }
    }

    public List<UserDTO> getFollowers(UUID userId) {

        User user = userDAO.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with the ID: " + userId));
        return user.getFollowers().stream()
                .map(follower -> convertToUserDTO(follower.getUser()))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getFollowing(UUID userId) {

        User user = userDAO.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with the ID: " + userId));
        return user.getFollowing().stream()
                .map(follower -> convertToUserDTO(follower.getFollowedUser()))
                .collect(Collectors.toList());
    }

    private UserDTO convertToUserDTO(User user) {

        //map the user object to a userDTO object

        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getFullName(),
                user.getProfilePicture());
    }
}
