package com.adso.listatareas.dto;

import lombok.Data;

@Data
public class HttpGolbalResponseDTO<T> {
    
    private String message;

    private Boolean success;

    private T data;
}
