package com.adso.tienda.dto.categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data 
public class RegisterCategoriesRequestDTO {
    
    @NotBlank (message = "el campo no puede ir vacio o null")
    @NotNull (message = "el campo no puede ir vacio o null")
    private String name;
}
