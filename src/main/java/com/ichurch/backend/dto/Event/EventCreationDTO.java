package com.ichurch.backend.dto.Event;

import com.ichurch.backend.enums.EventStatus;
import com.ichurch.backend.model.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.UUID;

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
    private Timestamp startDate;
    @NotNull(message = "Ending date can't be null.")
    private Timestamp endDate;
    @NotNull(message = "Status can't be null.")
    private EventStatus status;
    @NotNull(message = "Image URL can't be null.")
    private String imageUrl;
    @NotNull
    private UUID userId;
    private Long number;

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
                .imageUrl(String.valueOf(dto.getImageUrl()))
                .number(dto.getNumber())
                .build();
    }
}
