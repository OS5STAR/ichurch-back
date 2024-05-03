package com.ichurch.backend.repository;

import com.ichurch.backend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepo extends JpaRepository<Event, UUID> {
}
