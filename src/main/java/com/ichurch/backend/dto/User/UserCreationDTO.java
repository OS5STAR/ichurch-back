package com.ichurch.backend.dto.User;

import com.ichurch.backend.enums.UserRole;
import com.ichurch.backend.model.User;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationDTO {

    @NotNull
    @Email
    private String email;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    @NotBlank
    private String cpf;
    @NotNull
    @NotBlank
    private UserRole role;

    @AssertTrue
    private static boolean isValidCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11)
            return false;

        if (cpf.matches("(\\d)\\1{10}"))
            return false;

        int soma = 0;
        for (int i = 0; i < 9; i++)
            soma += (cpf.charAt(i) - '0') * (10 - i);
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9)
            digito1 = 0;

        soma = 0;
        for (int i = 0; i < 10; i++)
            soma += (cpf.charAt(i) - '0') * (11 - i);
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9)
            digito2 = 0;

        return (digito1 == cpf.charAt(9) - '0') && (digito2 == cpf.charAt(10) - '0');
    }

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
