package com.ichurch.backend.repository;

import com.ichurch.backend.model.SubEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubEventRepo extends JpaRepository<SubEvent, UUID> {
}
