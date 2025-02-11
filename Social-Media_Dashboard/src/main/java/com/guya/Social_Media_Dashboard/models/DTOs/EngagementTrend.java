package com.guya.Social_Media_Dashboard.models.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EngagementTrend {
    private String week;
    private int likes;
    private int comments;

    public EngagementTrend(String week, int likes, int comments) {
        this.week = week;
        this.likes = likes;
        this.comments = comments;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
