package com.adso.tienda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.tienda.dto.HttpGlobalresponseDTO;
import com.adso.tienda.dto.MessageResponseDTO;
import com.adso.tienda.dto.RegisterAdminDTO;
import com.adso.tienda.dto.UserDTO;
import com.adso.tienda.dto.categories.RegisterCategoriesRequestDTO;
import com.adso.tienda.dto.categories.RegisterGategoriesResponse;
import com.adso.tienda.enums.RoleEnum;
import com.adso.tienda.security.RequiresRole;
import com.adso.tienda.service.CategoriesService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/categories/")
@RequiredArgsConstructor 
public class CategoriesController {

    private final CategoriesService categoriesService;

    @PostMapping ("")
    @RequiresRole ({RoleEnum.ADMIN})
    public ResponseEntity<HttpGlobalresponseDTO<RegisterGategoriesResponse>> registerCategories(@Valid @RequestBody RegisterCategoriesRequestDTO request) {
        HttpGlobalresponseDTO<RegisterGategoriesResponse> response = categoriesService.registerCategories(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping ("")
    public ResponseEntity<HttpGlobalresponseDTO<List<RegisterGategoriesResponse>>> lisCategories(){
        HttpGlobalresponseDTO<List<RegisterGategoriesResponse>> response = categoriesService.listCategories();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping ("{id}")
    @RequiresRole ({RoleEnum.ADMIN})
    public ResponseEntity<HttpGlobalresponseDTO<MessageResponseDTO>> deleteCategories(@PathVariable Long id){
        HttpGlobalresponseDTO<MessageResponseDTO> response = categoriesService.deleteCategories(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
