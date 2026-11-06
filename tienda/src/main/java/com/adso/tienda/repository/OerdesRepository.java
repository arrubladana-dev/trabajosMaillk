package com.adso.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.adso.tienda.entity.Ordes;

import jakarta.transaction.Transactional;

@Transactional
public interface OerdesRepository  extends JpaRepository<Ordes,Long>{

    List<Ordes> findByClientId(Long clientId);

    @Procedure(procedureName = "pa_get_ordes_seller")
    List<Ordes> findOrdersBySellerId(@Param("p_seller_id") Long sellerId);
}
