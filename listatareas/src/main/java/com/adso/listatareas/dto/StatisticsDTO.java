package com.adso.listatareas.dto;

import lombok.Data;

@Data
public class StatisticsDTO {
    
    private Integer earrings;

    private Integer inProgress;

    private Integer completed;

    private Integer cancelled;

    private Integer all;
}
