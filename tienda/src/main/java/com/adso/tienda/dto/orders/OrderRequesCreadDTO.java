package com.adso.tienda.dto.orders;

import lombok.Data;

@Data 
public class OrderRequesCreadDTO {
    
    private Long idProduct;
    
    private Integer quantity;
}
