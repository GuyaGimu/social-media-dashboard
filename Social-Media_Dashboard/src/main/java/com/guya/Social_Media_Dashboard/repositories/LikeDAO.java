package com.guya.Social_Media_Dashboard.repositories;

import com.guya.Social_Media_Dashboard.models.Like;
import com.guya.Social_Media_Dashboard.models.Post;
import com.guya.Social_Media_Dashboard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LikeDAO extends JpaRepository<Like, UUID> {

    //Check if a user has already liked a specific post
    boolean existsByUserAndPost(User user, Post post);

    @Query("SELECT FUNCTION('DATE_FORMAT', l.createdAt, '%Y-%u') AS week, COUNT(l.id) " +
            "FROM Like l WHERE l.post.user.userId = :userId GROUP BY week ORDER BY week ASC")
    List<Object[]> findLikesGroupByWeek(UUID  userId);
}
