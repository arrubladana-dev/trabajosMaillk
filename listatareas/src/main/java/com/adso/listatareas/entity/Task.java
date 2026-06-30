package com.adso.listatareas.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.adso.listatareas.enums.PriorityTask;
import com.adso.listatareas.enums.StateTask;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Task {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "title")
    private String title;

    @Size(min = 3)
    @Column(name = "description")
    private String description;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private StateTask state;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private PriorityTask priority;

    @Column(name = "creation_date")
    private LocalDateTime creationdate;

    @Column(name = "date_of_uptade")
    private LocalDateTime dateOfUptade;

    @PrePersist
    public void createDate() {
        creationdate = LocalDateTime.now();
        dateOfUptade = LocalDateTime.now();
    }

    @PreUpdate
    public void uptadeOfDate() {
        dateOfUptade = LocalDateTime.now();
    }

}
