package com.adso.carvajal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adso.carvajal.dto.HttpGlobalResponseDTO;
import com.adso.carvajal.entity.Catalog;
import com.adso.carvajal.repository.CatalogRepositoty;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogService {
    
    private final CatalogRepositoty catalogRepositoty;

    @Transactional
    public HttpGlobalResponseDTO<List<Object[]>> getCatalogProducts(){
        
        List<Object[]> getCatalog = catalogRepositoty.getCatalog();
        HttpGlobalResponseDTO<List<Object[]>> response = new HttpGlobalResponseDTO<>();

        List<Catalog> listProducts = catalogRepositoty.findAll();
        
        if (listProducts.isEmpty()) {
            HttpGlobalResponseDTO<List<Object[]>> response1 = new HttpGlobalResponseDTO<>();
            response1.setMessage("El catalogo esta vacio");
            return response1;
        }
        

        
        response.setData(getCatalog);
        response.setMessage(";)");

        return response;
    }
}
