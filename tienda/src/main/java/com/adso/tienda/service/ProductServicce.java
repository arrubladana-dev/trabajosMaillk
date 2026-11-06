package com.adso.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adso.tienda.dto.HttpGlobalresponseDTO;
import com.adso.tienda.dto.MessageResponseDTO;
import com.adso.tienda.dto.products.RequestRegisterProductsDTO;
import com.adso.tienda.dto.products.ResponseProductDTO;
import com.adso.tienda.dto.products.ResponseProductsListDTO;
import com.adso.tienda.entity.Categories;
import com.adso.tienda.entity.Products;
import com.adso.tienda.entity.Users;
import com.adso.tienda.enums.RoleEnum;
import com.adso.tienda.repository.CategoriesRepository;
import com.adso.tienda.repository.ProductsRepository;
import com.adso.tienda.repository.UsersRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ProductServicce {
    
    private final ProductsRepository productsRepository;

    private final CategoriesRepository categotiesRepository;

    private final UsersRepository usersRepository;

    public HttpGlobalresponseDTO<List<ResponseProductsListDTO>> catalogProducts() {
        HttpGlobalresponseDTO<List<ResponseProductsListDTO>> response = new HttpGlobalresponseDTO<>();
        List<ResponseProductsListDTO> listResponse = new ArrayList<>();

        List<Products> listFouProducts = productsRepository.findAll();

        if (listFouProducts.isEmpty()) {
            response.setMessage("No hay ningun producto en la lista -w-");
            response.setSuccess(false);
            return response;
        }
        for (Products products : listFouProducts) {
            ResponseProductsListDTO productsFinal = new ResponseProductsListDTO();
            productsFinal.setId(products.getId());
            productsFinal.setName(products.getName());
            productsFinal.setPrice(products.getPrice());
            productsFinal.setStock(products.getStock());

            listResponse.add(productsFinal);
        }
        
        response.setData(listResponse);
        response.setMessage("Se ontuvo la lista con exito");
        response.setSuccess(true);
        return response;
    }

    public HttpGlobalresponseDTO<ResponseProductDTO> registrateProduct(Long userId, RequestRegisterProductsDTO request) {
        HttpGlobalresponseDTO<ResponseProductDTO> response = new HttpGlobalresponseDTO<>();
        ResponseProductDTO productDTO = new ResponseProductDTO();

        Optional<Products> productsFound = productsRepository.findByName(request.getName());
        Optional<Categories> categoriesFound = categotiesRepository.findById(request.getIdCategory());

        if (productsFound.isPresent()) {
            response.setMessage("Ya existe un producto con ese nombre -w-");
            response.setSuccess(false);
            return response;
        }

        if (categoriesFound.isEmpty()) {
            response.setMessage("No existe una categoria con ese id -w-");
            response.setSuccess(false);
            return response;
        }

        Products products = new Products();
        products.setName(request.getName());
        products.setDescription(request.getDescription());
        products.setPrice(request.getPrice());
        products.setStock(request.getStock());
        products.setIdCategory(request.getIdCategory());
        products.setIdSeller(userId);

        productsRepository.save(products);

        productDTO.setId(userId);
        productDTO.setName(request.getName());
        productDTO.setDescription(request.getDescription());
        productDTO.setPrice(request.getPrice());
        productDTO.setStock(request.getStock());
        productDTO.setIdCategory(request.getIdCategory());
        productDTO.setIdSeller(userId);

        response.setData(productDTO);
        response.setMessage("Se guardo con exito");
        response.setSuccess(true);

        return response;
    }

    public HttpGlobalresponseDTO<List<ResponseProductDTO>> getProductSeller(Long userId) {
        HttpGlobalresponseDTO<List<ResponseProductDTO>> response = new HttpGlobalresponseDTO<>();
        List<ResponseProductDTO> listResponse = new ArrayList<>();

        List<Products> listFound = productsRepository.findByIdSeller(userId);

        if (listFound.isEmpty()) {
            response.setMessage("No hay productos en su lista -w-");
            response.setSuccess(false);
            return response;
        }

        for (Products products : listFound) {
            ResponseProductDTO productsFinal = new ResponseProductDTO();
            productsFinal.setId(products.getId());
            productsFinal.setName(products.getName());
            productsFinal.setDescription(products.getDescription());
            productsFinal.setIdCategory(products.getIdCategory());
            productsFinal.setIdSeller(products.getIdSeller());
            productsFinal.setPrice(products.getPrice());
            productsFinal.setStock(products.getStock());

            listResponse.add(productsFinal);
        }
        
        response.setData(listResponse);
        response.setMessage("Se ontuvo la lista con exito");
        response.setSuccess(true);
        return response;

    }

    public HttpGlobalresponseDTO<ResponseProductDTO> putProduct(Long userId, Long id,RequestRegisterProductsDTO request) {
        HttpGlobalresponseDTO<ResponseProductDTO> response = new HttpGlobalresponseDTO<>();
        ResponseProductDTO productDTO = new ResponseProductDTO();

        Optional<Products> productsFound = productsRepository.findById(id);
        Optional<Categories> categoriesFound = categotiesRepository.findById(request.getIdCategory());
        Optional<Users> usersFoud = usersRepository.findById(userId);

        if (productsFound.isEmpty()) {
            response.setMessage("No existe un producto con ese id -w-");
            response.setSuccess(false);
            return response;
        }

        if (categoriesFound.isEmpty()) {
            response.setMessage("No existe una categoria con ese id -w-");
            response.setSuccess(false);
            return response;
        }

        Products products = productsFound.get();
        Users user = usersFoud.get();

        if (RoleEnum.fromId(user.getRolId())!= RoleEnum.ADMIN && products.getIdSeller() != user.getId()) {
            response.setMessage("No tiene permitido editar este producto -w-");
            response.setSuccess(false);
            return response;
        }

        
        products.setName(request.getName());
        products.setDescription(request.getDescription());
        products.setPrice(request.getPrice());
        products.setStock(request.getStock());
        products.setIdCategory(request.getIdCategory());

        productsRepository.save(products);

        productDTO.setId(products.getId());
        productDTO.setName(request.getName());
        productDTO.setDescription(request.getDescription());
        productDTO.setPrice(request.getPrice());
        productDTO.setStock(request.getStock());
        productDTO.setIdCategory(request.getIdCategory());
        productDTO.setIdSeller(products.getIdSeller());

        response.setData(productDTO);
        response.setMessage("Se actualizo con exito");
        response.setSuccess(true);

        return response;
    }

    public HttpGlobalresponseDTO<MessageResponseDTO> deleteProduct(Long userId, Long id) {
        HttpGlobalresponseDTO<MessageResponseDTO> response = new HttpGlobalresponseDTO<>();
        MessageResponseDTO messageResponse = new MessageResponseDTO();

        Optional<Products> productsFound = productsRepository.findById(id);
        Optional<Users> usersFoud = usersRepository.findById(userId);

        if (productsFound.isEmpty()) {
            response.setMessage("No existe un producto con ese id -w-");
            response.setSuccess(false);
            return response;
        }

        Products products = productsFound.get();
        Users user = usersFoud.get();

        if (RoleEnum.fromId(user.getRolId())!= RoleEnum.ADMIN && products.getIdSeller() != user.getId()) {
            response.setMessage("No tiene permitido eliminar este producto -w-");
            response.setSuccess(false);
            return response;
        }

        productsRepository.delete(products);

        messageResponse.setMessage("Se elimino con exito");
        response.setData(messageResponse);
        response.setMessage(";)");
        response.setSuccess(true);

        return response;
    }
}
