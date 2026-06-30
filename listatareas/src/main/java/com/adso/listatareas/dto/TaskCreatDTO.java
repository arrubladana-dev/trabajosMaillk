package com.adso.listatareas.dto;

import com.adso.listatareas.enums.PriorityTask;
import com.adso.listatareas.enums.StateTask;

import lombok.Data;

@Data
public class TaskCreatDTO {
    private String title;

    private String description;

    private StateTask stateTask;

    private PriorityTask priority;
}
