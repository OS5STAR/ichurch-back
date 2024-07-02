package com.ichurch.backend.dto.SubEvent;

import com.ichurch.backend.dto.Listener.ListenerCreationDTO;
import com.ichurch.backend.dto.Speaker.SpeakerCreationDTO;
import com.ichurch.backend.enums.EventStatus;
import com.ichurch.backend.model.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private Timestamp startDate;
    @NotNull
    private Timestamp endDate;
    @NotNull
    private EventStatus status;
    private List<ListenerCreationDTO> listeners;
    private List<SpeakerCreationDTO> speakers;
    private String place;

    @AssertTrue
    private boolean isDate(){
        return startDate.before(endDate);
    }

    public static SubEvent dtoToModel(SubEventCreationDTO dto) {

//        if (dto.getSpeakers() == null) {
//            dto.setSpeakers(new ArrayList<>());
//        }
//        if (dto.getListeners() == null) {
//            dto.setListeners(new ArrayList<>());
//        }

        return SubEvent.builder()
                .name(dto.getName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(dto.getStatus())
//                .listeners(dto.getListeners().stream().map(ListenerCreationDTO::dtoToModel).collect(Collectors.toList()))
//                .speakers(dto.getSpeakers().stream().map(SpeakerCreationDTO::dtoToModel).collect(Collectors.toList()))
                .listeners(new ArrayList<User>())
                .speakers(new ArrayList<User>())
                .place(dto.getPlace())
                .build();
    }
}