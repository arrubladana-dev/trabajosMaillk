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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/wish-list")
@RequiredArgsConstructor
public class WishListController {
    
    private final WishListService wishListService;

    @PostMapping("/create-wishList")
    public ResponseEntity<MessageResponseDTO> createWishList(@RequestBody WishListCreateDTO wishListCreateDTO) {
        try {
            MessageResponseDTO response = wishListService.createWishList(wishListCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("/list/{id}")
    public ResponseEntity<HttpGlobalResponseDTO<List<Object[]>>> getWishList(@PathVariable Long id) {
        try {
            HttpGlobalResponseDTO<List<Object[]>> response = wishListService.getWishList(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponseDTO> deleteWishList(@PathVariable Long id){
        try {
            MessageResponseDTO response = wishListService.deleteWishList(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("path/{id}")
    public ResponseEntity<HttpGlobalResponseDTO<List<Object[]>>> putWishList(@PathVariable Long id, @RequestBody WishListUpdateresponseDTO wUpdateresponseDTO) {
        try {
            HttpGlobalResponseDTO<List<Object[]>> response = wishListService.putWishList(id,wUpdateresponseDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }
}
