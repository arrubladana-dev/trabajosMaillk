package com.adso.tienda.dto.products;

import java.math.BigDecimal;import lombok.Data;

@Data 
public class ResponseProductsListDTO {
    
    private Long id;
    
    private String name;
    
    private BigDecimal price;
    
    private Integer stock;
}
