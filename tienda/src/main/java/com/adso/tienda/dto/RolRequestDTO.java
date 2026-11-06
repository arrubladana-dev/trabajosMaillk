package com.adso.tienda.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data 
public class RolRequestDTO {
    
    @NotNull (message = "no pude ser null esteb es ")
    private Long idRol;
}
