package com.ichurch.backend.dto.User;

import com.ichurch.backend.enums.UserRole;
import com.ichurch.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String cpf;
    private UserRole role;

    public static User dtoToModel(UserCreationDTO dto){

        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .cpf(dto.getCpf())
                .role(dto.getRole())
                .build();
    }

}
