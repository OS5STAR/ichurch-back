package com.ichurch.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private Event event;

    private String name;
    private Timestamp startDate;
    private Timestamp endDate;

    @ManyToMany(mappedBy = "subEvents")
    private List<Listener> listeners = new ArrayList<Listener>();

    private String[] speaker;
    private String place;
}
