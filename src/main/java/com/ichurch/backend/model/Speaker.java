package com.ichurch.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Table(indexes = {
        @Index(name = "idx_spkr_email", columnList = "email"),
        @Index(name = "idx_spkr_id", columnList = "id")
})
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private Integer age;
    private Boolean visitor = true;
    @ManyToMany
    @JoinTable(name = "subevent_speaker",
            joinColumns = @JoinColumn(name = "speaker_id"),
            inverseJoinColumns = @JoinColumn(name = "subevent_id"))
    private List<SubEvent> subEvents;

    @PrePersist
    private void onSave(){
        this.setSubEvents(new ArrayList<SubEvent>());
    }
}
