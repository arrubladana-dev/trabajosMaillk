package com.adso.tienda.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data 
public class RegisterAdminDTO {

    @NotBlank (message = "el campo no puede ir vacio")
    private String userName;

    @NotBlank (message = "el campo no puede ir vacio")
    private String email;

    @NotBlank (message = "el campo no puede ir vacio")
    private String password;

    private Long rol;

    private Boolean active;
}
