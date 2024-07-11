package com.ichurch.backend.controller;

import com.ichurch.backend.config.security.TokenService;
import com.ichurch.backend.dto.User.LoginResponseDTO;
import com.ichurch.backend.dto.User.UserAuthenticationDTO;
import com.ichurch.backend.dto.User.UserCreationDTO;
import com.ichurch.backend.dto.User.UserViewDTO;
import com.ichurch.backend.model.User;
import com.ichurch.backend.repository.UserRepo;
import com.ichurch.backend.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(UserViewDTO.modelToDto((User) auth.getPrincipal()), token,
                auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserCreationDTO dto) {

        return ResponseEntity.ok(authenticationService.save(dto));
    }
}
