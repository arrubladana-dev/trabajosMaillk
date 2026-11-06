package com.adso.tienda.dto.orders;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data 
public class OrderRequesCreadDTO {
    
    @NotNull (message = "el id del producto no puede ser null")
    private Long idProduct;
    
    @NotNull (message = "la cantidad no puede ser null")
    @Positive (message = "la cantidad debe ser mayor a 0")
    private Integer quantity;
}
