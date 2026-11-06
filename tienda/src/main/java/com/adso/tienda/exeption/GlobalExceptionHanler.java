package com.adso.tienda.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.adso.tienda.dto.HttpGlobalresponseDTO;

@RestControllerAdvice 
public class GlobalExceptionHanler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpGlobalresponseDTO<Object>> handleValidation(MethodArgumentNotValidException ex){

        HttpGlobalresponseDTO<Object> response = new HttpGlobalresponseDTO<>();

        response.setSuccess(false);
        response.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
        response.setData(null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpGlobalresponseDTO<Object>> handleException(Exception ex){

        HttpGlobalresponseDTO<Object> response = new HttpGlobalresponseDTO<>();

        response.setSuccess(false);
        response.setMessage("Ha ocurrido un error inesperado.");
        response.setData(null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
