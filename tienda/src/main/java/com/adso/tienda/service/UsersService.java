package com.adso.tienda.service;

import java.net.Authenticator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adso.tienda.dto.HttpGlobalresponseDTO;
import com.adso.tienda.dto.RegisterAdminDTO;
import com.adso.tienda.dto.RolRequestDTO;
import com.adso.tienda.dto.StatusRequestDTO;
import com.adso.tienda.dto.UserDTO;
import com.adso.tienda.entity.Roles;
import com.adso.tienda.entity.Users;
import com.adso.tienda.enums.RoleEnum;
import com.adso.tienda.repository.RolesRepository;
import com.adso.tienda.repository.UsersRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UsersService {

    private final PasswordEncoder passwordEncoder;

    private final UsersRepository usersRepository;

    private final RolesRepository rolesRepository;


    public HttpGlobalresponseDTO<UserDTO> profileUser(Long userId) {
        HttpGlobalresponseDTO<UserDTO> response = new HttpGlobalresponseDTO<>();
        UserDTO userDTO = new UserDTO();

        Optional<Users> userFound = usersRepository.findById(userId);

        if (userFound.isEmpty()) {
            response.setMessage("No se entontro usuario master");
            response.setSuccess(false);
            return response;
        }

        Users user = userFound.get();

        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRolId(RoleEnum.fromId(user.getRolId()));
        userDTO.setActive(user.getActive());

        response.setData(userDTO);
        response.setMessage("Se trajo con exito su infromacion :)");
        response.setSuccess(true);
        // Lond idUsers = jwtService.ectractRolId(request.get);

        return response;
        
    }

    public HttpGlobalresponseDTO<List<UserDTO>> listUser() {
        HttpGlobalresponseDTO<List<UserDTO>> response = new HttpGlobalresponseDTO<>();
        List<UserDTO> listDTO = new ArrayList<>();
        List<Users> useList = usersRepository.findAll();

        if(useList.isEmpty()){
            response.setMessage("no hay usuarios registrados");
            response.setSuccess(false);
            return response;
        }

        for (Users users : useList) {
            UserDTO userFinal = new UserDTO();
            userFinal.setUserName(users.getUserName());
            userFinal.setEmail(users.getEmail());
            userFinal.setRolId(RoleEnum.fromId(users.getRolId()));
            userFinal.setActive(users.getActive());

            listDTO.add(userFinal);
        }

        response.setData(listDTO);
        response.setMessage("Se encontraron los usuarios :)");
        response.setSuccess(true);

        return response;
    }

    public HttpGlobalresponseDTO<UserDTO> registerAdmin(RegisterAdminDTO request) {
        HttpGlobalresponseDTO<UserDTO> response = new HttpGlobalresponseDTO<>();
        UserDTO userDTO = new UserDTO();

        Optional<Users> userNamne = usersRepository.findByUserName(request.getUserName());
        Optional<Users> userEmail = usersRepository.findByEmail(request.getEmail());
        Optional<Roles> rolFoun = rolesRepository.findById(request.getRol());

        if (rolFoun.isEmpty()) {
            response.setMessage("No exixte ese rol._ .");
            response.setSuccess(false);
            return response;
        }

        if (userNamne.isPresent()) {
            response.setMessage("ya existe un usuario con ese nombre");
            response.setSuccess(false);
            return response;
        }

        if (userEmail.isPresent()) {
            response.setMessage("ya existe un usuario con ese email");
            response.setSuccess(false);
            return response;
        }

        Users user = new Users();

        user.setUserName(request.getUserName());   
        user.setEmail(request.getEmail());  
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(request.getActive());
        user.setRolId(request.getRol());

        usersRepository.save(user);

        userDTO.setUserName(request.getUserName());   
        userDTO.setEmail(request.getEmail());  
        userDTO.setActive(request.getActive());
        userDTO.setRolId(RoleEnum.fromId(request.getRol()));

        response.setData(userDTO);
        response.setMessage("se registro el usuario correctamente :)");
        response.setSuccess(true);


        return response;
    }

    public HttpGlobalresponseDTO<UserDTO> chengeRol(Long id, RolRequestDTO rol) {
        HttpGlobalresponseDTO<UserDTO> response = new HttpGlobalresponseDTO<>();
        UserDTO userDTO = new UserDTO();

        Optional<Users> userFound = usersRepository.findById(id);
        Optional<Roles> rolFoun = rolesRepository.findById(rol.getIdRol());

        if (userFound.isEmpty()) {
            response.setMessage("No exixte usuario con ese id ._ .");
            response.setSuccess(false);
            return response;
        }
        if (rolFoun.isEmpty()) {
            response.setMessage("No exixte ese rol._ .😑");
            response.setSuccess(false);
            return response;
        }
        

        Users user = userFound.get();

        user.setRolId(rol.getIdRol());
        usersRepository.save(user);

        userDTO.setId(id);
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRolId(RoleEnum.fromId(user.getRolId()));
        userDTO.setActive(user.getActive());

        response.setData(userDTO);
        response.setSuccess(true);
        response.setMessage("Se cambio el Rol con exito -w- ");


        return response;
    }

    public HttpGlobalresponseDTO<UserDTO> chengeStatus(Long id, StatusRequestDTO status) {
        HttpGlobalresponseDTO<UserDTO> response = new HttpGlobalresponseDTO<>();
        UserDTO userDTO = new UserDTO();

        Optional<Users> userFound = usersRepository.findById(id);

        if (userFound.isEmpty()) {
            response.setMessage("No exixte usuario con ese id ._ .");
            response.setSuccess(false);
            return response;
        }

        Users user = userFound.get();

        user.setActive(status.getStatus());
        usersRepository.save(user);

        userDTO.setId(id);
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRolId(RoleEnum.fromId(user.getRolId()));
        userDTO.setActive(user.getActive());

        response.setData(userDTO);
        response.setSuccess(true);
        response.setMessage("Se cambio el Estado con exito -w- ");


        return response;
    }
    
}

