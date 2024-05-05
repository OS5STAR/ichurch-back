package com.ichurch.backend.model;

import com.ichurch.backend.enums.EventStatus;
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
@Table(indexes = {@Index(name = "idx_subevnt_id", columnList = "id")})
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
    private EventStatus status;

    @ManyToMany(mappedBy = "subEvents")
    private List<Listener> listeners;

    @ManyToMany(mappedBy = "subEvents")
    private List<Speaker> speakers;
    private String place;

    @PrePersist
    private void onSave(){
        this.setListeners(new ArrayList<Listener>());
        this.setSpeakers(new ArrayList<Speaker>());
    }
}
