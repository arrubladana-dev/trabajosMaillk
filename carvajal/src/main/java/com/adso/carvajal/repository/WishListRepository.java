package com.adso.carvajal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adso.carvajal.entity.WishList;

import jakarta.transaction.Transactional;

@Transactional
public interface WishListRepository extends JpaRepository<WishList, Long> {
    
    @Procedure(procedureName = "pa_list_wish")
    List<Object[]> getWishList(@Param("id") Long id);

    List<WishList> findByIdUser(Long idUser);

    boolean existsByIdUserAndIdProducts(Long idUser, Long idProducts);
}
