package com.adso.tienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adso.tienda.entity.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long>{

    Optional<Categories> findByName(String name);
    
}
