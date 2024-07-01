package com.ichurch.backend.dto.Event;

import com.ichurch.backend.enums.EventStatus;
import com.ichurch.backend.model.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.validation.constraints.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventCreationDTO {

    @NotNull(message = "Name can't be null.")
    @NotBlank(message = "Name can't be empty.")
    @Size(max =  255, message = "Name can't be over 255 characters.")
    private String name;
    @NotNull(message = "Starting date can't be null.")
    @Future(message = "Event must start in a future date.")
    private Timestamp startDate;
    @NotNull(message = "Ending date can't be null.")
    @Future(message = "Must end in a future date.")
    private Timestamp endDate;
    @NotNull(message = "Status can't be null.")
    private EventStatus status;

    @AssertTrue(message = "Ending date must be after Starting date.")
    private boolean isDate(){
        return startDate.before(endDate);
    }

    public static Event dtoToModel(EventCreationDTO dto){
        return Event.builder()
                .name(dto.getName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(dto.getStatus())
                .build();
    }
}
