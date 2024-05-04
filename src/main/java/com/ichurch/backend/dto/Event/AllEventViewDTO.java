package com.ichurch.backend.dto.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllEventViewDTO {

    private List<EventViewDTO> events;
    private String quantity;


    public static AllEventViewDTO allEventViewDTO(List<EventViewDTO> eventViewList, Long qnt){
        return AllEventViewDTO.builder()
                .events(eventViewList)
                .quantity("Total Events: " + qnt)
                .build();
    }

}
