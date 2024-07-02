package com.ichurch.backend.service;

import com.ichurch.backend.customExceptions.ElementNotFoundException;
import com.ichurch.backend.customExceptions.RedundancyException;
import com.ichurch.backend.dto.User.UserCreationDTO;
import com.ichurch.backend.dto.User.UserViewDTO;
import com.ichurch.backend.model.User;
import com.ichurch.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username).orElseThrow(()->new ElementNotFoundException("User account not found"));
    }

    public UserViewDTO save(UserCreationDTO dto) {
        if(userRepo.findByEmail(dto.getEmail()).isPresent()) throw new IllegalArgumentException("User already registered");
        dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));

        return UserViewDTO.modelToDto(userRepo.save(UserCreationDTO.dtoToModel(dto)));
    }
}
