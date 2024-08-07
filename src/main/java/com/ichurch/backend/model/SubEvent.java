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

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "subevent_listener",
            joinColumns = @JoinColumn(name = "subevent_id"),
            inverseJoinColumns = @JoinColumn(name = "listener_id"))
    private List<User> listeners;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "subevent_speaker",
            joinColumns = @JoinColumn(name = "subevent_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private List<User> speakers;

    private String place;

    @PrePersist
    private void onSave(){
        this.setListeners(new ArrayList<>());
        this.setSpeakers(new ArrayList<>());
    }
}
