package com.ichurch.backend.dto.SubEvent;

import com.ichurch.backend.dto.User.UserViewDTO;
import com.ichurch.backend.model.SubEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
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
    private List<UserViewDTO> listeners;
    private List<UserViewDTO> speakers;
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
                .listeners(subEvent.getListeners().stream().map(UserViewDTO::modelToDto).collect(Collectors.toList()))
                .speakers(subEvent.getSpeakers().stream().map(UserViewDTO::modelToDto).collect(Collectors.toList()))
                .place(subEvent.getPlace())
                .build();
    }
}
