package com.adso.carvajal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.carvajal.dto.HttpGlobalResponseDTO;
import com.adso.carvajal.dto.MessageResponseDTO;
import com.adso.carvajal.service.CatalogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {
    
    private final CatalogService catalogService;

    @GetMapping("/list")
    public ResponseEntity<HttpGlobalResponseDTO<List<Object[]>>> getCatalogProducts() {
        try {
            HttpGlobalResponseDTO<List<Object[]>> response = catalogService.getCatalogProducts();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
