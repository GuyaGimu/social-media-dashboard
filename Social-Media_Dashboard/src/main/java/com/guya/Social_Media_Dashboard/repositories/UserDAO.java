package com.guya.Social_Media_Dashboard.repositories;

import com.guya.Social_Media_Dashboard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDAO extends JpaRepository<User, UUID> {

    // Check if a user exists by username
    boolean existsByUsername(String username);

    // Check if a user exists by email
    boolean existsByEmail(String email);

    // Optional: Find user by username or email (for login or other purposes)
    User findByUsername(String username);
    Optional<User> findByEmail(String email);

    Optional<User> findByUserId(UUID userId);

    //User loadUserByUsername(String username);
}
