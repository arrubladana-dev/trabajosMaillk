package com.adso.tienda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.tienda.dto.HttpGlobalresponseDTO;
import com.adso.tienda.dto.orders.OrderRequesCreadDTO;
import com.adso.tienda.dto.orders.OrdesResponseDTO;
import com.adso.tienda.dto.orders.UpdatteOrderStatusRequestDTO;
import com.adso.tienda.enums.RoleEnum;
import com.adso.tienda.security.RequiresRole;
import com.adso.tienda.service.OrdersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping ("/orders")
@RequiredArgsConstructor 
public class OrdesController {
    
    private final OrdersService ordersService;

    @PostMapping ("")
    @RequiresRole ({RoleEnum.CLIENTE})
    public ResponseEntity<HttpGlobalresponseDTO<OrdesResponseDTO>> creadOrder(@RequestAttribute("userId") Long userId, @Valid  @RequestBody OrderRequesCreadDTO request){
        HttpGlobalresponseDTO<OrdesResponseDTO> response = ordersService.creadOrder(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/mine")
    @RequiresRole ({RoleEnum.CLIENTE})
    public ResponseEntity<HttpGlobalresponseDTO<List<OrdesResponseDTO>>> getOrdersMine(@RequestAttribute("userId") Long userId) {
        HttpGlobalresponseDTO<List<OrdesResponseDTO>> response = ordersService.getOrdersMine(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/sales")
    @RequiresRole ({RoleEnum.VENDEDOR})
    public ResponseEntity<HttpGlobalresponseDTO<List<OrdesResponseDTO>>> getOrderSales(@RequestAttribute("userId") Long userId) {
        HttpGlobalresponseDTO<List<OrdesResponseDTO>> response = ordersService.getOrderSales(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("")
    @RequiresRole ({RoleEnum.ADMIN})
    public ResponseEntity<HttpGlobalresponseDTO<List<OrdesResponseDTO>>> getOrdersAdmin(@RequestAttribute("userId") Long userId) {
        HttpGlobalresponseDTO<List<OrdesResponseDTO>> response = ordersService.getOrdersAdmin(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @PatchMapping("/{id}/status")
    @RequiresRole ({RoleEnum.VENDEDOR, RoleEnum.ADMIN})
    public ResponseEntity<HttpGlobalresponseDTO<OrdesResponseDTO>> changeStatusOrder(@RequestAttribute("userId") Long userId, @PathVariable Long id,@RequestAttribute("rolId") Long rolId, @RequestBody UpdatteOrderStatusRequestDTO request){
        HttpGlobalresponseDTO<OrdesResponseDTO> response = ordersService.changeStatusOrder(userId,id,rolId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/cancel")
    @RequiresRole({RoleEnum.CLIENTE})
    public ResponseEntity<HttpGlobalresponseDTO<OrdesResponseDTO>> cancelOrder(@RequestAttribute("userId") Long userId, @PathVariable Long id) {
        HttpGlobalresponseDTO<OrdesResponseDTO> response = ordersService.cancelOrder(userId, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
