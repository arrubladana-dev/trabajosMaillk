package com.adso.futbol.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.futbol.dto.MessageResponseDTO;
import com.adso.futbol.dto.resulCoaching.ResponseResultCoachingDTO;
import com.adso.futbol.service.ResultCoachingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/resul-coaching")
@RequiredArgsConstructor
public class ResultController {
    
    private final ResultCoachingService resultCoachingService;

    @PostMapping("/pos-coaching")
    public ResponseEntity<MessageResponseDTO> createResultCoaching (@RequestBody ResponseResultCoachingDTO responseResulCoachingDTO){
        try {
            MessageResponseDTO response = resultCoachingService.createResultCoaching(responseResulCoachingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }

    @GetMapping("/final-team/{week}")
    public ResponseEntity<List<Object[]>> listFinalTeam(@PathVariable Integer week){
        try {
            List<Object[]> response = resultCoachingService.listFinalTeam(week);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
