package com.adso.futbol.dto;

import lombok.Data;

@Data
public class HttpGloblaResponseDTO<T> {
    private String message;

    private T data;
}
