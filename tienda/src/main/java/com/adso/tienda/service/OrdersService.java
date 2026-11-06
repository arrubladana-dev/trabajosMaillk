package com.adso.tienda.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adso.tienda.dto.HttpGlobalresponseDTO;
import com.adso.tienda.dto.orders.OrderRequesCreadDTO;
import com.adso.tienda.dto.orders.OrdesResponseDTO;
import com.adso.tienda.dto.orders.UpdatteOrderStatusRequestDTO;
import com.adso.tienda.entity.Ordes;
import com.adso.tienda.entity.Products;
import com.adso.tienda.enums.OrdesStatusEnum;
import com.adso.tienda.enums.RoleEnum;
import com.adso.tienda.repository.OerdesRepository;
import com.adso.tienda.repository.ProductsRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class OrdersService {
    
    private final OerdesRepository oerdesRepository;

    private final ProductsRepository productsRepository;

    public HttpGlobalresponseDTO<OrdesResponseDTO> creadOrder(Long userId, OrderRequesCreadDTO request) {
        HttpGlobalresponseDTO<OrdesResponseDTO> response = new HttpGlobalresponseDTO<>();
        OrdesResponseDTO ordeResponse = new OrdesResponseDTO();

        Optional<Products> producFound = productsRepository.findById(request.getIdProduct());

        if (producFound.isEmpty()) {
            response.setMessage("No hay un producto con ese id");
            response.setSuccess(false);
            return response;
        }

        Products produc = producFound.get();
        if (produc.getStock()<=0) {
            response.setMessage("No hay suficiente stock");
            response.setSuccess(false);
            return response;
        }

        produc.setStock(produc.getStock() - request.getQuantity());
        productsRepository.save(produc);

        Ordes order = new Ordes();
        order.setClientId(userId);
        order.setIdProduct(request.getIdProduct());
        order.setQuantity(request.getQuantity());

        BigDecimal total = produc.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()));
        order.setTotal(total);
        order.setStatus(OrdesStatusEnum.PENDIENTE);

        oerdesRepository.save(order);

        ordeResponse.setId(order.getId());
        ordeResponse.setIdClient(userId);
        ordeResponse.setIdProduct(request.getIdProduct());
        ordeResponse.setQuantity(request.getQuantity());
        ordeResponse.setTotal(total);
        ordeResponse.setStatus(OrdesStatusEnum.PENDIENTE);
        ordeResponse.setCreatedAt(order.getCreatedAt());

        response.setMessage("Se creo el pedido");
        response.setData(ordeResponse);
        response.setSuccess(true);

        return response;
    }

    
    public HttpGlobalresponseDTO<List<OrdesResponseDTO>> getOrdersMine(Long userId) {
        HttpGlobalresponseDTO<List<OrdesResponseDTO>> response = new HttpGlobalresponseDTO<>();
        List<OrdesResponseDTO> listResponse = new ArrayList<>();

        List<Ordes> ordesFound = oerdesRepository.findByClientId(userId);

        if (ordesFound.isEmpty()) {
            response.setMessage("No hay ningun pedido . - .");
            response.setSuccess(false);
            return response;
        }

        for (Ordes ordes : ordesFound) {
            OrdesResponseDTO ordesResponse = new OrdesResponseDTO();
            ordesResponse.setId(ordes.getId());
            ordesResponse.setIdClient(ordes.getClientId());
            ordesResponse.setIdProduct(ordes.getIdProduct());
            ordesResponse.setQuantity(ordes.getQuantity());
            ordesResponse.setTotal(ordes.getTotal());
            ordesResponse.setStatus(ordes.getStatus());
            ordesResponse.setCreatedAt(ordes.getCreatedAt());

            listResponse.add(ordesResponse);
        }

        response.setData(listResponse);
        response.setMessage("Se encontraron sus pedidos");
        response.setSuccess(true);

        return response;
    }

    @Transactional
    public HttpGlobalresponseDTO<List<OrdesResponseDTO>> getOrderSales(Long userId) {
        HttpGlobalresponseDTO<List<OrdesResponseDTO>> response = new HttpGlobalresponseDTO<>();
        List<OrdesResponseDTO> listResponse = new ArrayList<>();

        List<Ordes> ordersFound = oerdesRepository.findOrdersBySellerId(userId);

        if (ordersFound.isEmpty()) {
            response.setMessage("No hay pedidos para tus productos");
            response.setSuccess(false);
            return response;
        }

        for (Ordes ordes : ordersFound) {
            OrdesResponseDTO ordesResponse = new OrdesResponseDTO();
            ordesResponse.setId(ordes.getId());
            ordesResponse.setIdClient(ordes.getClientId());
            ordesResponse.setIdProduct(ordes.getIdProduct());
            ordesResponse.setQuantity(ordes.getQuantity());
            ordesResponse.setTotal(ordes.getTotal());
            ordesResponse.setStatus(ordes.getStatus());
            ordesResponse.setCreatedAt(ordes.getCreatedAt());

            listResponse.add(ordesResponse);
        }

        response.setData(listResponse);
        response.setMessage("Pedidos de tus productos encontrados");
        response.setSuccess(true);

        return response;
    }


    public HttpGlobalresponseDTO<List<OrdesResponseDTO>> getOrdersAdmin(Long userId) {
        HttpGlobalresponseDTO<List<OrdesResponseDTO>> response = new HttpGlobalresponseDTO<>();
        List<OrdesResponseDTO> listResponse = new ArrayList<>();

        List<Ordes> ordersFound = oerdesRepository.findAll();

        if (ordersFound.isEmpty()) {
            response.setMessage("No hay pedidos para tus productos");
            response.setSuccess(false);
            return response;
        }

        for (Ordes ordes : ordersFound) {
            OrdesResponseDTO ordesResponse = new OrdesResponseDTO();
            ordesResponse.setId(ordes.getId());
            ordesResponse.setIdClient(ordes.getClientId());
            ordesResponse.setIdProduct(ordes.getIdProduct());
            ordesResponse.setQuantity(ordes.getQuantity());
            ordesResponse.setTotal(ordes.getTotal());
            ordesResponse.setStatus(ordes.getStatus());
            ordesResponse.setCreatedAt(ordes.getCreatedAt());

            listResponse.add(ordesResponse);
        }

        response.setData(listResponse);
        response.setMessage("Pedidos de tus productos encontrados");
        response.setSuccess(true);

        return response;
    }

    public HttpGlobalresponseDTO<OrdesResponseDTO> changeStatusOrder(Long userId, Long id,Long rolId, UpdatteOrderStatusRequestDTO request) {
        HttpGlobalresponseDTO<OrdesResponseDTO> response = new HttpGlobalresponseDTO<>();
        OrdesResponseDTO ordeResponse = new OrdesResponseDTO();

        Optional<Ordes> orderFound = oerdesRepository.findById(id);

        if (orderFound.isEmpty()) {
            response.setMessage("No existe un pedido con ese id");
            response.setSuccess(false);
            return response;
        }

        Ordes order = orderFound.get();

        if (RoleEnum.fromId(rolId) == RoleEnum.VENDEDOR) {
            Optional<Products> productFound = productsRepository.findById(order.getIdProduct());

            if (productFound.isEmpty() || !productFound.get().getIdSeller().equals(userId)) {
                response.setMessage("No tiene permitido cambiar el estado de este pedido");
                response.setSuccess(false);
                return response;
            }
        }

        if (request.getStatus() == OrdesStatusEnum.CANCELADO) {
            response.setMessage("Para cancelar use el endpoint de cancelar pedido");
            response.setSuccess(false);
            return response;
        }

        if (order.getStatus() == OrdesStatusEnum.CANCELADO) {
            response.setMessage("No se puede cambiar el estado de un pedido cancelado");
            response.setSuccess(false);
            return response;
        }

        order.setStatus(request.getStatus());
        oerdesRepository.save(order);

        ordeResponse.setId(order.getId());
        ordeResponse.setIdClient(order.getClientId());
        ordeResponse.setIdProduct(order.getIdProduct());
        ordeResponse.setQuantity(order.getQuantity());
        ordeResponse.setTotal(order.getTotal());
        ordeResponse.setStatus(order.getStatus());
        ordeResponse.setCreatedAt(order.getCreatedAt());

        response.setData(ordeResponse);
        response.setMessage("Se cambio el estado del pedido :)");
        response.setSuccess(true);

        return response;
    }


    public HttpGlobalresponseDTO<OrdesResponseDTO> cancelOrder(Long userId, Long id) {
        HttpGlobalresponseDTO<OrdesResponseDTO> response = new HttpGlobalresponseDTO<>();
        OrdesResponseDTO ordeResponse = new OrdesResponseDTO();

        Optional<Ordes> orderFound = oerdesRepository.findById(id);

        if (orderFound.isEmpty()) {
            response.setMessage("No existe un pedido con ese id");
            response.setSuccess(false);
            return response;
        }

        Ordes order = orderFound.get();

        if (!order.getClientId().equals(userId)) {
            response.setMessage("No tiene permitido cancelar este pedido");
            response.setSuccess(false);
            return response;
        }

        if (order.getStatus() != OrdesStatusEnum.PENDIENTE) {
            response.setMessage("Solo se pueden cancelar pedidos en estado PENDIENTE");
            response.setSuccess(false);
            return response;
        }

        // Devolver el stock
        Optional<Products> productFound = productsRepository.findById(order.getIdProduct());
        if (productFound.isPresent()) {
            Products produc = productFound.get();
            produc.setStock(produc.getStock() + order.getQuantity());
            productsRepository.save(produc);
        }

        order.setStatus(OrdesStatusEnum.CANCELADO);
        oerdesRepository.save(order);

        ordeResponse.setId(order.getId());
        ordeResponse.setIdClient(order.getClientId());
        ordeResponse.setIdProduct(order.getIdProduct());
        ordeResponse.setQuantity(order.getQuantity());
        ordeResponse.setTotal(order.getTotal());
        ordeResponse.setStatus(order.getStatus());
        ordeResponse.setCreatedAt(order.getCreatedAt());

        response.setData(ordeResponse);
        response.setMessage("Se cancelo el pedido");
        response.setSuccess(true);

        return response;
    }




}
