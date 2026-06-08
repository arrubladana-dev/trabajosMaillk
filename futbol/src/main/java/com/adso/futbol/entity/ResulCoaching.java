package com.adso.futbol.entity;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "resul_coaching")
public class ResulCoaching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "power_shot")
    private Double powerShot;

    @Column(name = "velocity")
    private Double velocity;

    @Column(name = "passes")
    private Integer passes;
    
    @Column(name = "result")
    private Double result;

    @Column(name = "id_player")
    private Long idPlayer;

    @Column(name = "id_coaching")
    private Long idCoaching;
//     id int auto_increment primary key,
// power_shot int,
// velocity int,
// passes int,
// result double,

// id_player int,
// id_coaching int,
}
