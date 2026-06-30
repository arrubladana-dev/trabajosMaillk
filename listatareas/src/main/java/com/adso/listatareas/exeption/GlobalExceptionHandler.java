package com.adso.listatareas.exeption;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.adso.listatareas.dto.HttpGolbalResponseDTO;

public class GlobalExceptionHandler {
        @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpGolbalResponseDTO<Object> handleValidation(MethodArgumentNotValidException ex){

        HttpGolbalResponseDTO<Object> response = new HttpGolbalResponseDTO<>();

        response.setSuccess(false);
        response.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
        response.setData(null);

        return response;
    }

    @ExceptionHandler(Exception.class)
    public HttpGolbalResponseDTO<Object> handleException(Exception ex){

        HttpGolbalResponseDTO<Object> response = new HttpGolbalResponseDTO<>();

        response.setSuccess(false);
        response.setMessage("Ha ocurrido un error inesperado.");
        response.setData(null);

        return response;
    }
}
