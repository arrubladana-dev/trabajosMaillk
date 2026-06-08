package com.adso.futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adso.futbol.entity.Players;

public interface PlayersRepository extends JpaRepository<Players,Long> {

    
}
