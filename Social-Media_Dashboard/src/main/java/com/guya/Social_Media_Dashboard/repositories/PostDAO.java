package com.guya.Social_Media_Dashboard.repositories;

import com.guya.Social_Media_Dashboard.models.Post;
import com.guya.Social_Media_Dashboard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostDAO extends JpaRepository<Post, UUID> {

    List<Post> findPostByUserInOrderByCreatedAtDesc(List<User> users);

    @Query("SELECT p FROM Post p WHERE p.user.userId = :userId")
    List<Post> findByUser_UserId(@Param("userId") UUID userId);
    List<Post> findByUserInOrderByCreatedAtDesc(List<User> users);


    //List<Post> findByUserId(UUID userId);
}
