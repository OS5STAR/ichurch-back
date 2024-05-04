package com.ichurch.backend.dto.Event;

import com.ichurch.backend.CustomEx.ElementNotFoundException;
import com.ichurch.backend.dto.SubEvent.SubEventViewDTO;
import com.ichurch.backend.model.Event;
import com.ichurch.backend.model.SubEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventViewDTO {

    private UUID id;
    private String name;
    private List<SubEventViewDTO> subEvents;
    private Timestamp startDate;
    private Timestamp endDate;

    public static EventViewDTO modelToDto(Event event) {
//        if (event == null) throw new ElementNotFoundException("Event does not exist"); @TODO: por que ta aqui ?

//        List<SubEventViewDTO> subEventList = new ArrayList<>();
//
//        if(event.getSubEvents() != null && !event.getSubEvents().isEmpty()){
//            event.getSubEvents().forEach(subEvent -> subEventList.add(SubEventViewDTO.modelToDTO(subEvent)));
//        }

        return EventViewDTO.builder()
                .id(event.getId())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .subEvents(event.getSubEvents().stream().map(SubEventViewDTO::modelToDTO).collect(Collectors.toList()))
                .build();
    }
}
//subEvent.getSpeakers().stream().map(SpeakerViewDTO::modelToDTO).collect(Collectors.toList())