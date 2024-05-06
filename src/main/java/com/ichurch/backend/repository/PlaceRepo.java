package com.ichurch.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaceRepo extends JpaRepository<Place, UUID> {
}
