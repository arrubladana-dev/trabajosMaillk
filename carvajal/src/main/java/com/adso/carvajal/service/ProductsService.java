package com.adso.carvajal.service;

import org.springframework.stereotype.Service;

import com.adso.carvajal.repository.ProductsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsService {
    
    private final ProductsRepository productsRepository;
}
