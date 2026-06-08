package com.adso.futbol.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ResponseCoachingDTO {
    
    private LocalDate dateCoaching;

    private Integer week;

    private Integer numberCoaching;
}
