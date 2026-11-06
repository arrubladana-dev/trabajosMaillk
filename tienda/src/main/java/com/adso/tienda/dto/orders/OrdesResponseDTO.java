package com.adso.tienda.dto.orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.adso.tienda.enums.OrdesStatusEnum;
import lombok.Data;

@Data 
public class OrdesResponseDTO {
    
    private Long id;

    
    private Long idClient;

    
    private Long idProduct;

    
    private Integer quantity;

    
    private BigDecimal total;
    
    private OrdesStatusEnum status;

    private LocalDateTime createdAt;
}
