package com.ichurch.backend.controller;

import com.ichurch.backend.config.security.TokenService;
import com.ichurch.backend.dto.User.UserAuthenticationDTO;
import com.ichurch.backend.dto.User.UserCreationDTO;
import com.ichurch.backend.model.User;
import com.ichurch.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuthenticationDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCreationDTO dto){
        if(userRepo.findByEmail(dto.getEmail()) != null) return ResponseEntity.badRequest().build();

        dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        userRepo.save(UserCreationDTO.dtoToModel(dto));

        return ResponseEntity.ok().build();
    }
}
