package com.adso.tienda.dto.products;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data 
public class RequestRegisterProductsDTO {

    @NotBlank (message = "El campo no puede ir vacio")
    private String name;

    @NotBlank (message = "El campo no puede ir vacio")
    private String description;

    @NotNull (message = "El campo no puede ser null")
    private BigDecimal price;

    @NotNull (message = "El campo no puede ser null")
    private Integer stock;

    @NotNull (message = "El campo no puede ser null")
    private Long idCategory;

}
