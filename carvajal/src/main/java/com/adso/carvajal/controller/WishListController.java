package com.adso.carvajal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.carvajal.dto.HttpGlobalResponseDTO;
import com.adso.carvajal.dto.MessageResponseDTO;
import com.adso.carvajal.dto.WishListCreateDTO;
import com.adso.carvajal.dto.wishList.WishListUpdateresponseDTO;
import com.adso.carvajal.service.WishListService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/wish-list")
@RequiredArgsConstructor
public class WishListController {
    
    private final WishListService wishListService;

    @PostMapping("/create-wishList")
    public MessageResponseDTO createWishList(@RequestBody WishListCreateDTO wishListCreateDTO) {

        return wishListService.createWishList(wishListCreateDTO);
    }
    
    @GetMapping("/list/{id}")
    public HttpGlobalResponseDTO<List<Object[]>> getWishList(@PathVariable Long id) {
        return wishListService.getWishList(id);
    }
    
    @DeleteMapping("/delete/{id}")
    public MessageResponseDTO deleteWishList(@PathVariable Long id){
        return wishListService.deleteWishList(id);
    }

    @PutMapping("path/{id}")
    public HttpGlobalResponseDTO<List<Object[]>> putWishList(@PathVariable Long id, @RequestBody WishListUpdateresponseDTO wUpdateresponseDTO) {
        return wishListService.putWishList(id,wUpdateresponseDTO);
    }
}
