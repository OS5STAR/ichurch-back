package com.ichurch.backend.repository;

import com.ichurch.backend.model.Listener;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ListenerRepo extends JpaRepository<Listener, UUID> {

    Optional<Listener> findByEmail(String email);
}
