package com.adso.tienda.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data 
public class StatusRequestDTO {
    
    @NotNull (message = "el estado no puede ser null")
    private Boolean status;
}
