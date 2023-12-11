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
public class UserRequest {

    @NotBlank(message = "No debe dejar este campo vacio")
    private String firstname;

    @NotBlank(message = "No debe dejar este campo vacio")
    private String lastname;

    @NotBlank(message = "No debe dejar este campo vacio")
    @Email(message = "Email invalido")
    private String email;
}
