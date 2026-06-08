package com.adso.futbol.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adso.futbol.dto.MessageResponseDTO;
import com.adso.futbol.dto.ResponseFinalResultDTO;
import com.adso.futbol.dto.resulCoaching.ResponseResultCoachingDTO;
import com.adso.futbol.entity.Coaching;
import com.adso.futbol.entity.Players;
import com.adso.futbol.entity.ResulCoaching;
import com.adso.futbol.repository.CoachingRepository;
import com.adso.futbol.repository.PlayersRepository;
import com.adso.futbol.repository.ResultCoachingRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultCoachingService {

    private final ResultCoachingRepository resultCoachingRepository;
    private final PlayersRepository playersRepository;
    private final CoachingRepository coachingRepository;

    
    public MessageResponseDTO createResultCoaching(ResponseResultCoachingDTO responseResulCoachingDTO) {
        MessageResponseDTO response = new MessageResponseDTO();

        ResulCoaching resulCoaching = new ResulCoaching();
        resulCoaching.setPowerShot(responseResulCoachingDTO.getPowerShot());
        resulCoaching.setVelocity(responseResulCoachingDTO.getVelocity());
        resulCoaching.setPasses(responseResulCoachingDTO.getPasses());
        Double resultFinal = (responseResulCoachingDTO.getPowerShot()*0.2)+(responseResulCoachingDTO.getVelocity()*0.3)+(responseResulCoachingDTO.getPasses()*0.5);
        resulCoaching.setResult(resultFinal);
        resultCoachingRepository.save(resulCoaching);

        response.setMessage("se creo");

        return response;
    }

    @Transactional
    public List<Object[]> listFinalTeam(Integer week) {
        List<Coaching> coaching = coachingRepository.findByWeek(week);

        if (coaching.stream().anyMatch(c -> c.getNumberCoaching() == 3)) {
            List<Object[]> responseResult = resultCoachingRepository.getfinalTeam();
            return responseResult;
        } else {
            List<Object[]> res = new ArrayList<>();
            res.add(new Object[]{"Np hay suficiente informacion"});
            return res;
        }
        

        
    }
    
}
