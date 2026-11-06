package com.adso.tienda.dto;

import com.adso.tienda.enums.RoleEnum;

import lombok.Data;

@Data 
public class UserDTO {
    
    private Long id;

    private String userName;

    private String email;

    private Boolean active;

    private RoleEnum rolId;
}
