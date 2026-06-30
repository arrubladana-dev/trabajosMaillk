package com.adso.listatareas.dto;

import java.time.LocalDateTime;

import com.adso.listatareas.enums.PriorityTask;
import com.adso.listatareas.enums.StateTask;
import lombok.Data;

@Data
public class TaskResponseDTO {
    
    private String title;

    private String description;

    private StateTask state;

    private PriorityTask priority;

    private LocalDateTime creationdate;

    private LocalDateTime dateOfUptade;
}
