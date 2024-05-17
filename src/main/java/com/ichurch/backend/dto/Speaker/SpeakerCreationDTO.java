package com.ichurch.backend.dto.Speaker;

import com.ichurch.backend.model.Speaker;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class SpeakerCreationDTO {

    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @Email
    private String email;
    @Positive
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
