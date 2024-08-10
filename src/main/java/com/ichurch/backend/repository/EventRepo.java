package com.ichurch.backend.repository;

import com.ichurch.backend.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface EventRepo extends JpaRepository<Event, UUID>, PagingAndSortingRepository<Event, UUID> {

    Page<Event> findAllByNameContaining(String term, Pageable pageable);
}
