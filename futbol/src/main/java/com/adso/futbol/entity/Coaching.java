package com.adso.futbol.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "coaching")
public class Coaching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dateCoaching")
    private LocalDate dateCoaching;

    @Column(name = "week")
    private Integer week;

    @Column(name = "number_coaching ")
    private Integer numberCoaching;

    
}
