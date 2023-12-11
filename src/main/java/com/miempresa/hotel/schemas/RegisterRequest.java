package com.miempresa.hotel.schemas;

import com.miempresa.hotel.modelo.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "No puede dejar este campo vacio")
    private String firstname;
    @NotBlank(message = "No puede dejar este campo vacio")
    private String lastname;
    @NotBlank(message = "No puede dejar este campo vacio")
    private String username;
    @NotBlank(message = "No puede dejar este campo vacio")
    @Email(message = "Ingrese un email valido")
    private String email;
    @NotBlank(message = "No puede dejar este campo vacio")
    private String password;
    @NotNull(message = "No puede dejar este campo vacio")
    private Role role;
}
