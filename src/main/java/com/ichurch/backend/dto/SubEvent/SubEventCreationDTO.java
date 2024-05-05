package com.ichurch.backend.dto.SubEvent;

import com.ichurch.backend.dto.Listener.ListenerCreationDTO;
import com.ichurch.backend.dto.Speaker.SpeakerCreationDTO;
import com.ichurch.backend.enums.EventStatus;
import com.ichurch.backend.model.Event;
import com.ichurch.backend.model.Listener;
import com.ichurch.backend.model.Speaker;
import com.ichurch.backend.model.SubEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Data
public class SubEventCreationDTO {

    private Event event;
    private String name;
    private Timestamp startDate;
    private Timestamp endDate;
    private EventStatus status;
    private List<ListenerCreationDTO> listeners;
    private List<SpeakerCreationDTO> speakers;
    private String place;

    public static SubEvent dtoToModel(SubEventCreationDTO dto) {

//        if (dto.getSpeakers() == null) {
//            dto.setSpeakers(new ArrayList<>());
//        }
//        if (dto.getListeners() == null) {
//            dto.setListeners(new ArrayList<>());
//        }

        return SubEvent.builder()
                .event(dto.getEvent())
                .name(dto.getName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(dto.getStatus())
//                .listeners(dto.getListeners().stream().map(ListenerCreationDTO::dtoToModel).collect(Collectors.toList()))
//                .speakers(dto.getSpeakers().stream().map(SpeakerCreationDTO::dtoToModel).collect(Collectors.toList()))
                .listeners(new ArrayList<Listener>())
                .speakers(new ArrayList<Speaker>())
                .place(dto.getPlace())
                .build();
    }
}