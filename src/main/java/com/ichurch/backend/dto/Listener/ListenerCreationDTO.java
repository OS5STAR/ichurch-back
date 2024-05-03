package com.ichurch.backend.dto.Listener;


import com.ichurch.backend.model.Listener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ListenerCreationDTO {

    private String name;
    private String email;
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
