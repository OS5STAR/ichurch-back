package com.ichurch.backend.dto.SubEvent;

import com.ichurch.backend.dto.Listener.ListenerViewDTO;
import com.ichurch.backend.model.Event;
import com.ichurch.backend.model.SubEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class SubEventViewDTO {

    private UUID subEventId;
    private Timestamp startDate;
    private Timestamp endDate;
    private List<ListenerViewDTO> listener;
    private String[] speaker;
    private String place;

    public static SubEventViewDTO modelToDTO(SubEvent subEvent) {


        List<ListenerViewDTO> listenerList = new ArrayList<>();

        if(subEvent.getListeners() != null && !subEvent.getListeners().isEmpty()){
            subEvent.getListeners().forEach(l -> listenerList.add(ListenerViewDTO.modelToDTO(l)));
        }

        return SubEventViewDTO.builder()
                .subEventId(subEvent.getId())
                .startDate(subEvent.getStartDate())
                .endDate(subEvent.getEndDate())
                .listener(listenerList)
//                .speaker(subEvent.getListener())
                .place(subEvent.getPlace())
                .build();
    }
}
