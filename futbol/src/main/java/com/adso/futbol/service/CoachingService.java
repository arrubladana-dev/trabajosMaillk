package com.adso.futbol.service;

import org.springframework.stereotype.Service;

import com.adso.futbol.dto.MessageResponseDTO;
import com.adso.futbol.dto.ResponseCoachingDTO;
import com.adso.futbol.entity.Coaching;
import com.adso.futbol.repository.CoachingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoachingService {

    private final CoachingRepository coachingRepository;

    public MessageResponseDTO createCoaching(ResponseCoachingDTO responseCoachingDTO) {
        MessageResponseDTO response = new MessageResponseDTO();

        Coaching coaching = new Coaching();
        coaching.setDateCoaching(responseCoachingDTO.getDateCoaching());
        coaching.setWeek(responseCoachingDTO.getWeek());
        coaching.setNumberCoaching(responseCoachingDTO.getNumberCoaching());
        coachingRepository.save(coaching);

        response.setMessage("Se creo");


        return response;
    }
    
}
