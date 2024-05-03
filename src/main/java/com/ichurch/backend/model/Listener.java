package com.ichurch.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(indexes = {
        @Index(name = "idx_lstnr_email", columnList = "email"),
        @Index(name = "listener_lstnr_id", columnList = "id")
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Listener {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private Integer age;
    private Boolean visitor = true;

    @ManyToMany
    @JoinTable(name = "subevent_listener",
            joinColumns = @JoinColumn(name = "listener_id"),
            inverseJoinColumns = @JoinColumn(name = "subevent_id"))
    private List<SubEvent> subEvents;

    @PrePersist
    private void onSave(){
        this.subEvents = new ArrayList<SubEvent>();
    }
}
