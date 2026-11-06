package com.adso.tienda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.tienda.dto.HttpGlobalresponseDTO;
import com.adso.tienda.dto.MessageResponseDTO;
import com.adso.tienda.dto.products.RequestRegisterProductsDTO;
import com.adso.tienda.dto.products.ResponseProductDTO;
import com.adso.tienda.dto.products.ResponseProductsListDTO;
import com.adso.tienda.enums.RoleEnum;
import com.adso.tienda.security.RequiresRole;
import com.adso.tienda.service.ProductServicce;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController 
@RequestMapping ("/products")
@RequiredArgsConstructor 
public class ProductsController {
    
    private final ProductServicce productServicce;

    @GetMapping("")
    public ResponseEntity<HttpGlobalresponseDTO<List<ResponseProductsListDTO>>> catalogProducts() {
        HttpGlobalresponseDTO<List<ResponseProductsListDTO>> response = productServicce.catalogProducts();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @PostMapping("")
    @RequiresRole ({RoleEnum.VENDEDOR})
    public ResponseEntity<HttpGlobalresponseDTO<ResponseProductDTO>> registrateProduct(@RequestAttribute("userId") Long userId, @Valid @RequestBody RequestRegisterProductsDTO request) {
        HttpGlobalresponseDTO<ResponseProductDTO> response = productServicce.registrateProduct(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/mine")
    @RequiresRole ({RoleEnum.VENDEDOR})
    public ResponseEntity<HttpGlobalresponseDTO<List<ResponseProductDTO>>> getProductSeller(@RequestAttribute("userId") Long userId) {
        HttpGlobalresponseDTO<List<ResponseProductDTO>> response = productServicce.getProductSeller(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @PutMapping("/{id}")
    @RequiresRole ({RoleEnum.VENDEDOR, RoleEnum.ADMIN})
    public ResponseEntity<HttpGlobalresponseDTO<ResponseProductDTO>> putProduct(@RequestAttribute("userId") Long userId,@PathVariable Long id, @Valid @RequestBody RequestRegisterProductsDTO request) {
        HttpGlobalresponseDTO<ResponseProductDTO> response = productServicce.putProduct(userId,id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping ("/{id}")
    @RequiresRole ({RoleEnum.VENDEDOR, RoleEnum.ADMIN})
    public ResponseEntity<HttpGlobalresponseDTO<MessageResponseDTO>> deleteProduct(@RequestAttribute("userId") Long userId,@PathVariable Long id) {
        HttpGlobalresponseDTO<MessageResponseDTO> response = productServicce.deleteProduct(userId,id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
