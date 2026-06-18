package com.adso.carvajal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.adso.carvajal.entity.Products;

import jakarta.transaction.Transactional;

@Transactional
public interface ProductsRepository extends JpaRepository<Products, Long> {
    
}
