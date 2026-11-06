package com.adso.tienda.dto.categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class RegisterCategoriesRequestDTO {
    
    @NotBlank (message = "el campo no puede ir vacio o null")
    @NotNull (message = "el campo no puede ir vacio o null")
    @Size (max = 100, message = "El nombre no puede pasar de 100 caracteres")
    private String name;
}
