package com.guya.Social_Media_Dashboard.repositories;

import com.guya.Social_Media_Dashboard.models.Analytics;
import com.guya.Social_Media_Dashboard.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnalyticsDAO extends JpaRepository<Analytics, UUID> {

        Optional<Analytics> findByPost_PostId(UUID post);
}
