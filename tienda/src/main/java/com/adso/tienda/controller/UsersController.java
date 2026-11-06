package com.adso.tienda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.tienda.dto.HttpGlobalresponseDTO;
import com.adso.tienda.dto.RegisterAdminDTO;
import com.adso.tienda.dto.RolRequestDTO;
import com.adso.tienda.dto.StatusRequestDTO;
import com.adso.tienda.dto.UserDTO;
import com.adso.tienda.enums.RoleEnum;
import com.adso.tienda.security.RequiresRole;
import com.adso.tienda.service.UsersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping ("/users/")
@RequiredArgsConstructor 
public class UsersController {
    
    private final UsersService usersService;

    @GetMapping("me")
    public ResponseEntity<HttpGlobalresponseDTO<UserDTO>> profileUser(@RequestAttribute("userId") @Valid Long userId) {
        HttpGlobalresponseDTO<UserDTO> response = usersService.profileUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping ("")
    @RequiresRole ({RoleEnum.ADMIN})
    public ResponseEntity<HttpGlobalresponseDTO<List<UserDTO>>> listUsers() {
        HttpGlobalresponseDTO<List<UserDTO>> response = usersService.listUser();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @PostMapping ("")
    @RequiresRole ({RoleEnum.ADMIN})
    public ResponseEntity<HttpGlobalresponseDTO<UserDTO>> registerAdmin(@Valid @RequestBody RegisterAdminDTO request) {
        HttpGlobalresponseDTO<UserDTO> response = usersService.registerAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PatchMapping ("{id}/role")
    @RequiresRole ({RoleEnum.ADMIN})
    public ResponseEntity<HttpGlobalresponseDTO<UserDTO>> chengeRol(@PathVariable Long id, @Valid @RequestBody RolRequestDTO rol) {
        HttpGlobalresponseDTO<UserDTO> response = usersService.chengeRol(id, rol);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @PatchMapping ("{id}/status")
    @RequiresRole ({RoleEnum.ADMIN})
    public ResponseEntity<HttpGlobalresponseDTO<UserDTO>> chengeStatus(@PathVariable Long id, @Valid @RequestBody StatusRequestDTO status) {
        HttpGlobalresponseDTO<UserDTO> response = usersService.chengeStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
