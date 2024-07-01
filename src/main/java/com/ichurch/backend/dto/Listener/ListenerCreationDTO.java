package com.ichurch.backend.dto.Listener;


import com.ichurch.backend.model.Listener;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ListenerCreationDTO {

    @NotNull
    @NotBlank
    private String name;
    @Email
    @NotNull
    private String email;
    @Positive
    private Integer age;
    private Boolean visitor;

    public static Listener dtoToModel(ListenerCreationDTO dto){
        return Listener.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .visitor(dto.getVisitor())
                .build();
    }

}
