package com.adso.tienda.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data 
public class RegisterAdminDTO {

    @NotBlank (message = "el campo no puede ir vacio")
    private String userName;

    @NotBlank (message = "el campo no puede ir vacio")
    private String email;

    @NotBlank (message = "el campo no puede ir vacio")
    private String password;

    @NotNull (message = "el rol no puede ser null")
    @Positive (message = "el rol no es valido")
    private Long rol;

    @NotNull (message = "el campo no puede ser null")
    private Boolean active;
}
