package com.adso.tienda.dto.products;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class RequestRegisterProductsDTO {

    @NotBlank (message = "El campo no puede ir vacio")
    @Size (max = 150, message = "El nombre no puede pasar de 150 caracteres")
    private String name;

    @NotBlank (message = "El campo no puede ir vacio")
    @Size (max = 500, message = "La descripcion no puede pasar de 500 caracteres")
    private String description;

    @NotNull (message = "El campo no puede ser null")
    @Positive (message = "El precio debe ser mayor a 0")
    private BigDecimal price;

    @NotNull (message = "El campo no puede ser null")
    @PositiveOrZero (message = "El stock no puede ser negativo")
    private Integer stock;

    @NotNull (message = "El campo no puede ser null")
    private Long idCategory;

}
