package com.ichurch.backend.dto.Speaker;

import com.ichurch.backend.model.Speaker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class SpeakerViewDTO {

    private String name;
    private String email;
    private Integer age;
    private Boolean visitor;


    public static SpeakerViewDTO modelToDTO(Speaker speaker){

        return SpeakerViewDTO.builder()
                .name(speaker.getName())
                .email(speaker.getEmail())
                .age(speaker.getAge())
                .visitor(speaker.getVisitor())
                .build();
    }
}
