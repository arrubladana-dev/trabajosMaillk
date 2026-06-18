package com.adso.carvajal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.carvajal.dto.HttpGlobalResponseDTO;
import com.adso.carvajal.service.CatalogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {
    
    private final CatalogService catalogService;

    @GetMapping("/list")
    public HttpGlobalResponseDTO<List<Object[]>> getCatalogProducts() {
        return catalogService.getCatalogProducts();
    }
}
