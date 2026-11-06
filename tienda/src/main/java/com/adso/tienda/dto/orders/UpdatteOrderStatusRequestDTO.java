package com.adso.tienda.dto.orders;

import com.adso.tienda.enums.OrdesStatusEnum;

import lombok.Data;

@Data 
public class UpdatteOrderStatusRequestDTO {
    
    private OrdesStatusEnum status;
}
