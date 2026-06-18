package com.adso.carvajal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adso.carvajal.dto.HttpGlobalResponseDTO;
import com.adso.carvajal.dto.MessageResponseDTO;
import com.adso.carvajal.dto.WishListCreateDTO;
import com.adso.carvajal.dto.wishList.WishListUpdateresponseDTO;
import com.adso.carvajal.entity.Products;
import com.adso.carvajal.entity.WishList;
import com.adso.carvajal.repository.ProductsRepository;
import com.adso.carvajal.repository.WishListRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishListService {
    
    private final WishListRepository wishListRepository;
    private final ProductsRepository productsRepository;

    public MessageResponseDTO createWishList(WishListCreateDTO wishListCreateDTO) {
        MessageResponseDTO response = new MessageResponseDTO();

        WishList wishList = new WishList();

        wishList.setIdProducts(wishListCreateDTO.getIdProducts());
        wishList.setIdUser(wishListCreateDTO.getIdUser());
        wishListRepository.save(wishList);

        response.setMessage("se añadio a la lista");

        return response;
    }

    @Transactional
    public HttpGlobalResponseDTO<List<Object[]>> getWishList(Long id) {

        List<Object[]> getWList = wishListRepository.getWishList(id);
        HttpGlobalResponseDTO<List<Object[]>>  response = new HttpGlobalResponseDTO<>();


        List<WishList> listWis = wishListRepository.findByIdUser(id);
        
        if(listWis.isEmpty()){
            HttpGlobalResponseDTO<List<Object[]>>  response1 = new HttpGlobalResponseDTO<>();
            response1.setData(getWList);
            response1.setMessage("Su lista de deseos esta vacia");
            return response1;}
        
        String nameProduc = " ";

        for (WishList wish : listWis) {
            Optional<Products> producFount = productsRepository.findById(wish.getIdProducts());
            Products productsFinal = producFount.get();

            if (productsFinal.getStock() <= 0) {
                nameProduc += (nameProduc.isEmpty() ? "" : ", ") + productsFinal.getNameProduct();
            }
        }
        if (!nameProduc.isEmpty()) {
            response.setMessage("no hay stock del producto: " + nameProduc);
        } else {
            response.setMessage("sus pruductos :) " );
        }
        response.setData(getWList);


        return response;
    }

    public MessageResponseDTO deleteWishList(Long id) {
        
        MessageResponseDTO response = new MessageResponseDTO();

        Optional<WishList> wishLis = wishListRepository.findById(id);

        if (wishLis.isEmpty()) {
            response.setMessage("este elemento no se encuentra");
            return response;
        }
        wishListRepository.deleteById(id);
        response.setMessage("se elimino con exito");

        return response;
    }

    public HttpGlobalResponseDTO<List<Object[]>> putWishList(Long id, WishListUpdateresponseDTO wUpdateresponseDTO) {

        HttpGlobalResponseDTO<List<Object[]>> response = new HttpGlobalResponseDTO<>();
        
        Optional<WishList> wishLis = wishListRepository.findById(id);
    if (wishLis.isEmpty()) {
        response.setMessage("este elemento no se encuentra");
        return response;
    }

    WishList wish = wishLis.get();

    boolean yaExiste = wishListRepository.existsByIdUserAndIdProducts(wish.getIdUser(), wUpdateresponseDTO.getIdProduct());
    if (yaExiste) {
        response.setMessage("este producto ya se encuentra en tu lista de deseos");
        return response;
    }


    wish.setIdProducts(wUpdateresponseDTO.getIdProduct());
    wishListRepository.save(wish);
    response.setData(wishListRepository.getWishList(id));
    response.setMessage("se actualizo con exito");
    return response;

    }
}
