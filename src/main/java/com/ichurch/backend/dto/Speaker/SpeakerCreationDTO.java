package com.ichurch.backend.dto.Speaker;

import com.ichurch.backend.model.Speaker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class SpeakerCreationDTO {

    private String name;
    private String email;
    private Integer age;
    private Boolean visitor;

    public static Speaker dtoToModel(SpeakerCreationDTO dto){
        return Speaker.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .visitor(dto.getVisitor())
                .build();
    }
}
