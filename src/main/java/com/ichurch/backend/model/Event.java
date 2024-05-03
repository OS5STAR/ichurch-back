package com.ichurch.backend.model;

import com.ichurch.backend.enums.EventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Table(indexes = {
        @Index(name = "idx_event_name", columnList = "name"),
        @Index(name = "idx_event_id", columnList = "id")
})
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private EventStatus status;
    private Timestamp startDate;
    private Timestamp endDate;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubEvent> subEvents = new ArrayList<SubEvent>();


}

