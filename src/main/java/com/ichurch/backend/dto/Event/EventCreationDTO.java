package com.ichurch.backend.dto.Event;

import com.ichurch.backend.dto.SubEvent.SubEventCreationDTO;
import com.ichurch.backend.model.Event;
import com.ichurch.backend.model.SubEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventCreationDTO {

    private String name;
    private Timestamp startDate;
    private Timestamp endDate;

    public static Event dtoToModel(EventCreationDTO dto){
        if(dto == null) return null;

        return Event.builder()
                .name(dto.getName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
