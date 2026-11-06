package com.adso.tienda.dto.categories;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data 
public class RegisterGategoriesResponse {

    @NotBlank (message = "el campo no puede ir vacio o null")
    private Long idCategories;

    @NotBlank (message = "el campo no puede ir vacio o null")
    private String name;
}
