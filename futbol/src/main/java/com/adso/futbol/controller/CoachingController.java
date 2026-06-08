package com.adso.futbol.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.futbol.dto.MessageResponseDTO;
import com.adso.futbol.dto.ResponseCoachingDTO;
import com.adso.futbol.service.CoachingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coaching")
@RequiredArgsConstructor
public class CoachingController {
    
    private final CoachingService coachingService;

    @PostMapping("/pos-coaching")
    public ResponseEntity<MessageResponseDTO> createCoaching (@RequestBody ResponseCoachingDTO responseCoachingDTO){
        try {
            MessageResponseDTO response = coachingService.createCoaching(responseCoachingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //return coachingService.createCoaching(responseCoachingDTO);
    }
}
