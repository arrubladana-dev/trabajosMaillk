package com.adso.tienda.dto;

import lombok.Data;

@Data 
public class HttpGlobalresponseDTO <T> {
    
    private T data;

    private String message;

    private Boolean success;

}
