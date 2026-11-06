package com.adso.tienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adso.tienda.entity.Products;
import java.util.List;


public interface ProductsRepository extends  JpaRepository<Products,Long>{

    Optional<Products> findByName(String name);

    List<Products> findByIdSeller(Long idSeller);
}
