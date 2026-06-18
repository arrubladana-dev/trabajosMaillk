package com.adso.carvajal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.adso.carvajal.entity.Catalog;

import jakarta.transaction.Transactional;

@Transactional
public interface CatalogRepositoty extends JpaRepository<Catalog, Long> {
    @Procedure(procedureName = "pa_list_catalog_products")
    List<Object[]> getCatalog();
}
