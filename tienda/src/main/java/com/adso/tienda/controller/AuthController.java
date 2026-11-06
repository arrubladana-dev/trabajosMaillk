package com.adso.tienda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.tienda.dto.HttpGlobalresponseDTO;
import com.adso.tienda.dto.LoginRequesDTO;
import com.adso.tienda.dto.LoginResponseDTO;
import com.adso.tienda.dto.RegisterRequestDTO;
import com.adso.tienda.dto.UserDTO;
import com.adso.tienda.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/auth/")
@RequiredArgsConstructor 
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<HttpGlobalresponseDTO<UserDTO>> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        try {
            HttpGlobalresponseDTO<UserDTO> response = authService.register(registerRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
        
    }
    
    @PostMapping("login")
    public ResponseEntity<HttpGlobalresponseDTO<LoginResponseDTO>> login(@Valid @RequestBody LoginRequesDTO requesDto) {
        HttpGlobalresponseDTO<LoginResponseDTO> response = authService.login(requesDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @PostMapping("refrestoken")
    public ResponseEntity<HttpGlobalresponseDTO<LoginResponseDTO>> refresToken(HttpServletRequest request) {
        HttpGlobalresponseDTO<LoginResponseDTO> response = new HttpGlobalresponseDTO<>();
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setMessage("Token is invalid");
            response.setSuccess(false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        
        String token = authHeader.replace("Bearer ", "");

        try {
            response = authService.refreshToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Token is invalid or expired");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    
}
