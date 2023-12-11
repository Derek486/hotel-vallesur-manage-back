package com.miempresa.hotel.schemas;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordRequest {
    @NotBlank(message = "Ingrese una contraseña")
    private String password;

    @NotBlank(message = "Ingrese la confirmación de contraseña")
    private String passwordConfirm;
}
