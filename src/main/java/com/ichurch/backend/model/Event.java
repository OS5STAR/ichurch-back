package com.ichurch.backend.model;

import com.ichurch.backend.enums.EventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.processing.Generated;
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

    private Long number;
    private String name;
    @Enumerated(EnumType.STRING)
    private EventStatus status;
    private Timestamp startDate;
    private Timestamp endDate;
    private String imageUrl;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubEvent> subEvents = new ArrayList<SubEvent>();
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User createdBy;

    @PrePersist
    private void onSave() {
        this.subEvents = new ArrayList<SubEvent>();
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}

