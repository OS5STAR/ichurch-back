package com.ichurch.backend.dto.SubEvent;

import com.ichurch.backend.model.Event;
import com.ichurch.backend.model.SubEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@Data
public class SubEventCreationDTO {

    private Event event;
    private String name;
    private Timestamp startDate;
    private Timestamp endDate;
    private String[] listener;
    private String[] speaker;
    private String place;

    public static SubEvent dtoToModel(SubEventCreationDTO dto) {
        return SubEvent.builder()
                .event(dto.getEvent())
                .name(dto.getName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
//                .listener(dto.getListener())
//                .speaker(dto.getSpeaker())
                .place(dto.getPlace())
                .build();
    }
}
