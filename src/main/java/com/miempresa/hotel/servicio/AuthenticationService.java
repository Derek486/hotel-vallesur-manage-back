package com.miempresa.hotel.servicio;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.miempresa.hotel.interfaces.IUser;
import com.miempresa.hotel.modelo.User;
import com.miempresa.hotel.schemas.AuthenticationRequest;
import com.miempresa.hotel.schemas.AuthenticationResponse;
import com.miempresa.hotel.schemas.RegisterRequest;
import com.miempresa.hotel.schemas.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IUser repository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager; 
    
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .user(
                UserResponse.builder()
                    .email(user.getEmail())
                    .lastname(user.getLastname())
                    .firstname(user.getFirstname())
                    .role("ROLE_"+user.getRole())
                    .username(user.getUsername())
                    .build())
            .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(), 
                request.getPassword()
            )
        );
        var user = repository.findByEmail(request.getEmail())
            .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .user(
                UserResponse.builder()
                    .email(user.getEmail())
                    .lastname(user.getLastname())
                    .firstname(user.getFirstname())
                    .role("ROLE_"+user.getRole())
                    .username(user.getUsername())
                    .build()
            ).build();
    }
    
    public void guardarContraseña(User usuario, String password) {
        usuario.setPassword(passwordEncoder.encode(password));
        repository.save(usuario);
    }

}
