package com.guya.Social_Media_Dashboard.controllers;

import com.guya.Social_Media_Dashboard.models.DTOs.PostEngagement;
import com.guya.Social_Media_Dashboard.services.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/{userId}/post-engagement")
    public ResponseEntity<List<PostEngagement>> getUserPostEngagement(@PathVariable UUID userId) {
        List<PostEngagement> userPostEngagement = analyticsService.getUserPostEngagements(userId);
        return ResponseEntity.ok(userPostEngagement);
    }

    @GetMapping("/{userId}/engagement-trends")
    public ResponseEntity<List<PostEngagement>> getEngagementTrends(@PathVariable UUID userId) {
        List<PostEngagement> engagementTrends = analyticsService.getUserPostEngagements(userId);
        return ResponseEntity.ok(engagementTrends);
    }
}
