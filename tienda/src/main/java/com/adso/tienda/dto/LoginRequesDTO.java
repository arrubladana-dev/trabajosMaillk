package com.adso.tienda.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data 
public class LoginRequesDTO {
    
    @NotBlank (message = "el campo no puede ir vacio")
    private String user;


    @NotBlank (message = "el campo no puede ir vacio")
    private String password;
}
