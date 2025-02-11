package com.guya.Social_Media_Dashboard.services;

import com.guya.Social_Media_Dashboard.models.Analytics;
import com.guya.Social_Media_Dashboard.models.Comment;
import com.guya.Social_Media_Dashboard.models.DTOs.EngagementTrend;
import com.guya.Social_Media_Dashboard.models.DTOs.PostEngagement;
import com.guya.Social_Media_Dashboard.models.Like;
import com.guya.Social_Media_Dashboard.models.Post;
import com.guya.Social_Media_Dashboard.repositories.AnalyticsDAO;
import com.guya.Social_Media_Dashboard.repositories.CommentDAO;
import com.guya.Social_Media_Dashboard.repositories.LikeDAO;
import com.guya.Social_Media_Dashboard.repositories.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final PostDAO postDAO;

    private final AnalyticsDAO AnalyticsDAO;

    private LikeDAO likeDAO;
    private CommentDAO commentDAO;

    @Autowired
    public AnalyticsService(PostDAO postDAO, AnalyticsDAO AnalyticsDAO, LikeDAO likeDAO, CommentDAO commentDAO) {
        this.postDAO = postDAO;
        this.AnalyticsDAO = AnalyticsDAO;
        this.likeDAO = likeDAO;
        this.commentDAO = commentDAO;
    }

    public List<PostEngagement> getUserPostEngagements(UUID userId) {

        List<Post> userPosts = postDAO.findByUser_UserId(userId);

        if (userPosts.isEmpty()) {
            throw new RuntimeException("No posts found for userId: " + userId);
        }

        return userPosts.stream()
                .map(this::mapPostToPostEngagement)
                .collect(Collectors.toList());
    }

    private PostEngagement mapPostToPostEngagement(Post post) {
        Analytics analytics = AnalyticsDAO.findByPost_PostId(post.getPostId())
                .orElseThrow(() -> new RuntimeException("Analytics not found for post: " + post.getPostId()));

        int likeCount = post.getLikes() != null ? post.getLikes().size()  :  0;
        int commentCount = post.getComments() != null ? post.getComments().size(): 0;
        int viewCount = analytics.getViews();
        int shareCount = analytics.getShares();

        return new PostEngagement(
                post.getPostId(),
                post.getContent(),
                likeCount,
                commentCount,
                viewCount,
                shareCount
        );
    }

    public List<EngagementTrend> getUserEngagementTrend(UUID  userId){

        List<Object[]> likesTrends = likeDAO.findLikesGroupByWeek(userId);
        List<Object[]> commentsTrends = commentDAO.findCommentsGroupByWeek(userId);

        Map<String, EngagementTrend> trendsMap = new HashMap<>();

        for(Object[] row : likesTrends){
            String week = (String) row[0];
            int likesCount = ((int) row[1]);
            trendsMap.putIfAbsent(week, new EngagementTrend(week, 0, 0));
            trendsMap.get(week).setLikes(likesCount);
        }

        for(Object[] row : commentsTrends){
            String week = (String) row[0];
            int commentsCount = ((int) row[1]);
            trendsMap.putIfAbsent(week, new EngagementTrend(week, 0, 0));
            trendsMap.get(week).setComments(commentsCount);
        }
        return trendsMap.values().stream()
                .sorted(Comparator.comparing(EngagementTrend::getWeek))
                .collect(Collectors.toList());
    }
}
