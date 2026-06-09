package com.adso.futbol.service;

import org.springframework.stereotype.Service;

import com.adso.futbol.dto.MessageResponseDTO;
import com.adso.futbol.dto.players.ResponsePlayersDTO;
import com.adso.futbol.entity.Players;
import com.adso.futbol.repository.PlayersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayersService {
    
    private final PlayersRepository playersRepository;

    public MessageResponseDTO createPlayers(ResponsePlayersDTO responsePlayersDTO) {
        MessageResponseDTO response = new MessageResponseDTO();

        Players player = new Players();
        player.setName(responsePlayersDTO.getName());
        player.setPhone(responsePlayersDTO.getPhone());
        playersRepository.save(player);

        response.setMessage("se creo");

        return response;
    }
}
