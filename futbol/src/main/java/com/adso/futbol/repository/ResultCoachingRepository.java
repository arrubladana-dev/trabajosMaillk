package com.adso.futbol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adso.futbol.entity.ResulCoaching;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ResultCoachingRepository extends JpaRepository<ResulCoaching, Long> {

    @Procedure(procedureName = "pa_final_team")
    List<Object[]> getfinalTeam();
}
