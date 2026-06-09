package com.adso.futbol.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.futbol.dto.MessageResponseDTO;
import com.adso.futbol.dto.players.ResponsePlayersDTO;
import com.adso.futbol.repository.PlayersRepository;
import com.adso.futbol.service.PlayersService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayersController {
    
    private final PlayersService playersService;

    @PostMapping("post-players")
    public ResponseEntity<MessageResponseDTO> createPlayers(@RequestBody ResponsePlayersDTO responsePlayersDTO) {
        try {
            MessageResponseDTO response = playersService.createPlayers(responsePlayersDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //return playersService.createPlayers(responsePlayersDTO);
    }
    
}
