package com.ichurch.backend.repository;

import com.ichurch.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    Optional<UserDetails> findByEmail (String email);
}
