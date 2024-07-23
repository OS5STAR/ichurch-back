package com.ichurch.backend.repository;

import com.ichurch.backend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface EventRepo extends JpaRepository<Event, UUID>, PagingAndSortingRepository<Event, UUID> {
}
