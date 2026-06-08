package com.adso.futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adso.futbol.entity.Coaching;
import java.util.List;
import java.util.Optional;


public interface CoachingRepository extends JpaRepository<Coaching,Long> {
    List<Coaching> findByWeek(Integer week);
}
