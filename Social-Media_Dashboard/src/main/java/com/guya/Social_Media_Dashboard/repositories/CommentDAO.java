package com.guya.Social_Media_Dashboard.repositories;

import com.guya.Social_Media_Dashboard.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentDAO extends JpaRepository<Comment, UUID> {

    @Query("SELECT FUNCTION('DATE_FORMAT', c.createdAt, '%Y-%u') AS week, COUNT(c.id) " +
            "FROM Comment c WHERE c.post.user.id = :userId GROUP BY week ORDER BY week ASC")
    List<Object[]> findCommentsGroupByWeek(UUID userId);
}
