package com.adso.tienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adso.tienda.entity.Users;

public interface UsersRepository  extends JpaRepository<Users, Long>{

    Optional<Users> findByUserName(String userName);

    Optional<Users> findByEmail(String email);
    
}
