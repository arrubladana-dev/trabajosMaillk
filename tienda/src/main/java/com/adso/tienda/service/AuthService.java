package com.adso.tienda.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adso.tienda.dto.HttpGlobalresponseDTO;
import com.adso.tienda.dto.LoginRequesDTO;
import com.adso.tienda.dto.LoginResponseDTO;
import com.adso.tienda.dto.RegisterRequestDTO;
import com.adso.tienda.dto.UserDTO;
import com.adso.tienda.entity.Users;
import com.adso.tienda.enums.RoleEnum;
import com.adso.tienda.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService {
    
    private final PasswordEncoder passwordEncoder;

    private final UsersRepository usersRepository;

    private final JwtService jwtService;


    public HttpGlobalresponseDTO<UserDTO> register(RegisterRequestDTO registerRequestDTO) {
        
        HttpGlobalresponseDTO<UserDTO> response = new HttpGlobalresponseDTO<>();
        UserDTO userResponse = new UserDTO();

        Optional<Users> userFound = usersRepository.findByUserName(registerRequestDTO.getUserName());
        Optional<Users> emailFpund = usersRepository.findByEmail(registerRequestDTO.getEmail());

        
        if(userFound.isPresent()){
            response.setMessage("El nombre del usuario ya se encuentra en uso buscate otro");
            
            return response;
        }

        if (emailFpund.isPresent()) {
            response.setMessage("el correo ya se encuentra en uso");
            return response;
        }
        

        
        Users user = new Users();
        user.setUserName(registerRequestDTO.getUserName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setActive(registerRequestDTO.getActive());
        user.setRolId(RoleEnum.CLIENTE.getId());

        usersRepository.save(user);

        userResponse.setUserName(registerRequestDTO.getUserName());
        userResponse.setEmail(registerRequestDTO.getEmail());
        userResponse.setActive(registerRequestDTO.getActive());
        userResponse.setRolId(RoleEnum.CLIENTE);

        response.setData(userResponse);
        response.setMessage("Se registro con exito :)");;

        return  response;
    }


    public HttpGlobalresponseDTO<LoginResponseDTO> login(LoginRequesDTO requesDto) {
        HttpGlobalresponseDTO<LoginResponseDTO> response = new HttpGlobalresponseDTO<>();
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        Optional<Users> userFoundName = usersRepository.findByUserName(requesDto.getUser());
        Optional<Users> userFoundEmail = usersRepository.findByEmail(requesDto.getUser());

        if (userFoundName.isEmpty() && userFoundEmail.isEmpty() ) {
            response.setMessage("usuario o contraseña incorrectos");
            return response;
        }

        Users userFound;
        if (userFoundName.isPresent()) {
            userFound = userFoundName.get();
        }else{
            userFound = userFoundEmail.get();
        }

        if(!passwordEncoder.matches( requesDto.getPassword(), userFound.getPassword())){
            response.setMessage("usuario o contraseña incorrectos . _ .");
            return response;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(userFound.getUserName());
        userDTO.setEmail(userFound.getEmail());
        userDTO.setActive(userFound.getActive());
        userDTO.setRolId(RoleEnum.fromId(userFound.getRolId()));

        String jwt = jwtService.generateToken(userFound.getId(), userFound.getUserName(), userFound.getRolId());
        loginResponseDTO.setJwt(jwt);
        loginResponseDTO.setUser(userDTO);

        response.setData(loginResponseDTO);
        response.setMessage("Ha ingrasado correctamente ;)");
        response.setSuccess(true);


        return response;
    }


    public HttpGlobalresponseDTO<LoginResponseDTO> refreshToken(String token) throws Exception {
        HttpGlobalresponseDTO<LoginResponseDTO> response = new HttpGlobalresponseDTO<>();
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        UserDTO userDTO = new UserDTO();
        String jwt = jwtService.refresToken(token);
        Long idToken = jwtService.extractUserId(token);

        Optional<Users> userFound = usersRepository.findById(idToken);

        if (userFound.isEmpty()) {
            response.setMessage("usuario no encontrado");
            response.setSuccess(false);
            return response;
        }
        
        Users userFinal = userFound.get();

        userDTO.setUserName(userFinal.getUserName());
        userDTO.setEmail(userFinal.getEmail());
        userDTO.setRolId(RoleEnum.fromId(userFinal.getRolId()));
        userDTO.setActive(userFinal.getActive());

        loginResponseDTO.setJwt(jwt);
        loginResponseDTO.setUser(userDTO);

        response.setData(loginResponseDTO);
        response.setMessage("Se refreco el token (._.)/");
        response.setSuccess(true);
        return response;
        
    }
}
