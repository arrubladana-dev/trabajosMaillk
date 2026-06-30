package com.adso.listatareas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adso.listatareas.entity.Task;
import com.adso.listatareas.enums.StateTask;
import java.util.List;
import com.adso.listatareas.enums.PriorityTask;




public interface TaskRepository extends JpaRepository<Task,Long> {
    Optional<Task> findByTitle(String title);
    List<Task> findByState(StateTask state);
    List<Task> findByPriority(PriorityTask priority);
}
