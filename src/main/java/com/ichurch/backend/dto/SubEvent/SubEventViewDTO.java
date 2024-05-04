package com.ichurch.backend.dto.SubEvent;

import com.ichurch.backend.dto.Listener.ListenerViewDTO;
import com.ichurch.backend.dto.Speaker.SpeakerViewDTO;
import com.ichurch.backend.model.Event;
import com.ichurch.backend.model.SubEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Builder
public class SubEventViewDTO {

    private UUID subEventId;
    private Timestamp startDate;
    private Timestamp endDate;
    private List<ListenerViewDTO> listener;
    private List<SpeakerViewDTO> speaker;
    private String place;

    public static SubEventViewDTO modelToDTO(SubEvent subEvent) {

        if (subEvent.getSpeakers() == null) {
            throw new RuntimeException("Speakers list is null");
        }
        if (subEvent.getListeners() == null) {
            throw new RuntimeException("Listeners list is null");
        }

        return SubEventViewDTO.builder()
                .subEventId(subEvent.getId())
                .startDate(subEvent.getStartDate())
                .endDate(subEvent.getEndDate())
                .listener(subEvent.getListeners().stream().map(ListenerViewDTO::modelToDTO).collect(Collectors.toList()))
                .speaker(subEvent.getSpeakers().stream().map(SpeakerViewDTO::modelToDTO).collect(Collectors.toList()))
                .place(subEvent.getPlace())
                .build();
    }
}
