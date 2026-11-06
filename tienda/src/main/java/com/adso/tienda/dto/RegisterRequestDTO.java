package com.adso.tienda.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class RegisterRequestDTO {

    @NotBlank (message = "el campo no puede ir vacio")
    @Size (max = 50, message = "el nombre de usuario no puede pasar de 50 caracteres")
    private String userName;

    @NotBlank (message = "el campo no puede ir vacio")
    @Email (message = "el email no es valido")
    @Size (max = 255, message = "el email no puede pasar de 255 caracteres")
    private String email;

    @NotBlank (message = "el campo no puede ir vacio")
    private String password;

    @NotNull (message = "el campo no puede ser null")
    private Boolean active;

}
