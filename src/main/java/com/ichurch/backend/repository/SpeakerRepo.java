package com.ichurch.backend.repository;

import com.ichurch.backend.model.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpeakerRepo extends JpaRepository<Speaker, UUID> {

    Optional<Speaker> findByEmail(String email);

}
