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
public class UserAgenteRequest {
    @NotBlank(message = "No debe dejar este campo vacio")
    private String firstname;

    @NotBlank(message = "No debe dejar este campo vacio")
    private String lastname;
}
