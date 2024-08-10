package com.ichurch.backend.dto.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllEventViewDTO {

    private List<EventViewDTO> events;
    private Map<String, Object> page;

    public static AllEventViewDTO allEventViewDTO(List<EventViewDTO> eventViewList, Pageable pageable, int totalPages){
        HashMap<String, Object> map = new HashMap<>();
        map.put("paged", true);
        map.put("offset", pageable.getOffset());
        map.put("pageNumber", pageable.getPageNumber());
        map.put("totalPages", totalPages);

        return AllEventViewDTO.builder()
                .events(eventViewList)
                .page(map)
                .build();
    }

    public static AllEventViewDTO allEventViewDTO(List<EventViewDTO> eventViewList){


        return AllEventViewDTO.builder()
                .events(eventViewList)
                .build();
    }

}
