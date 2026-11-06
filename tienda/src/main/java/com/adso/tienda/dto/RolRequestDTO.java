package com.adso.tienda.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data 
public class RolRequestDTO {
    
    @NotNull (message = "el id del rol no puede ser null")
    @Positive (message = "el id del rol no es valido")
    private Long idRol;
}
