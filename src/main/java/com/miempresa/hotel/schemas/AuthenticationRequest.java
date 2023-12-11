package com.miempresa.hotel.schemas;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = "Ingrese su email")
    @Email(message = "Formato de email invalido")
    private String email;

    @NotBlank(message = "Ingrese una contraseña")
    private String password;
}
