package com.ichurch.backend.dto.Listener;

import com.ichurch.backend.model.Listener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ListenerViewDTO {

    private String name;
    private String email;
    private Integer age;
    private Boolean visitor;

    public static ListenerViewDTO modelToDTO(Listener listener){
        return ListenerViewDTO.builder()
                .name(listener.getName())
                .email(listener.getEmail())
                .age(listener.getAge())
                .visitor(listener.getVisitor())
                .build();
    }
}
