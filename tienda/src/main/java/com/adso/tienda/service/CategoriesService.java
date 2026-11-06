package com.adso.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adso.tienda.dto.HttpGlobalresponseDTO;
import com.adso.tienda.dto.MessageResponseDTO;
import com.adso.tienda.dto.UserDTO;
import com.adso.tienda.dto.categories.RegisterCategoriesRequestDTO;
import com.adso.tienda.dto.categories.RegisterGategoriesResponse;
import com.adso.tienda.entity.Categories;
import com.adso.tienda.entity.Users;
import com.adso.tienda.enums.RoleEnum;
import com.adso.tienda.repository.CategoriesRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CategoriesService {
    
    private final CategoriesRepository categoriesRepository;

    public HttpGlobalresponseDTO<RegisterGategoriesResponse> registerCategories(RegisterCategoriesRequestDTO request) {
        HttpGlobalresponseDTO<RegisterGategoriesResponse> response = new HttpGlobalresponseDTO<>();
        RegisterGategoriesResponse reqgisterResponse = new RegisterGategoriesResponse();

            Optional<Categories> categoriesFiund = categoriesRepository.findByName(request.getName());

            if (categoriesFiund.isPresent()) {
                response.setMessage("Ya hay una categoria con ese nombre :(");
                response.setSuccess(false);
                return response;
            }

            Categories categories = new Categories();
            categories.setName(request.getName());
            categoriesRepository.save(categories);

            reqgisterResponse.setIdCategories(categories.getId());
            reqgisterResponse.setName(categories.getName());

            response.setData(reqgisterResponse);
            response.setMessage("se registro la categoria con exito ");
            response.setSuccess(true);

        return response;

    }

    public HttpGlobalresponseDTO<List<RegisterGategoriesResponse>> listCategories() {
        HttpGlobalresponseDTO<List<RegisterGategoriesResponse>> response = new HttpGlobalresponseDTO<>();
        List<RegisterGategoriesResponse> listDTO = new ArrayList<>();
        List<Categories> categoriesListFound = categoriesRepository.findAll();

        if(categoriesListFound.isEmpty()){
            response.setMessage("no hay categorias registradas");
            response.setSuccess(false);
            return response;
        }

        for (Categories categories : categoriesListFound) {
            RegisterGategoriesResponse cateforiesFinal = new RegisterGategoriesResponse();
            cateforiesFinal.setIdCategories(categories.getId());
            cateforiesFinal.setName(categories.getName());


            listDTO.add(cateforiesFinal);
        }

        response.setData(listDTO);
        response.setMessage("Se encontraron las categorias :)");
        response.setSuccess(true);

        return response;
    }

    public HttpGlobalresponseDTO<MessageResponseDTO> deleteCategories(Long id) {
        HttpGlobalresponseDTO<MessageResponseDTO> response = new HttpGlobalresponseDTO<>();
        MessageResponseDTO messageResponse = new MessageResponseDTO();

        Optional<Categories> categoriesFound = categoriesRepository.findById(id);

        if (categoriesFound.isEmpty()) {
            response.setMessage("No se encontro ninguna categoria por ese id :(");
            response.setSuccess(false);
            return response;
        }

        Categories categories = categoriesFound.get();
        categoriesRepository.delete(categories);
        messageResponse.setMessage("Se eliminon con exito la categoria");
        response.setData(messageResponse);
        response.setMessage("'w'");
        response.setSuccess(true);
        return response;
    }


}
