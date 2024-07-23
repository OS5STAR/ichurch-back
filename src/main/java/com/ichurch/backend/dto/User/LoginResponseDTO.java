package com.ichurch.backend.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private UserViewDTO user;
    private String token;
    private List<String> roles;
}
