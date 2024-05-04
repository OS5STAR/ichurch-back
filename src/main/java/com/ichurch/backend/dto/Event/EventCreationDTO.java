package com.ichurch.backend.dto.Event;

import com.ichurch.backend.enums.EventStatus;
import com.ichurch.backend.model.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventCreationDTO {


    private String name;
    private Timestamp startDate;
    private Timestamp endDate;
    private EventStatus status;

    public static Event dtoToModel(EventCreationDTO dto){

        return Event.builder()
                .name(dto.getName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(dto.getStatus())
                .build();
    }
}
