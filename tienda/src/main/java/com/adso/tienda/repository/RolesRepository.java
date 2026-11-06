package com.adso.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adso.tienda.entity.Roles;

public interface RolesRepository  extends  JpaRepository<Roles, Long>{
    
}
