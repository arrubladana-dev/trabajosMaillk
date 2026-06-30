package com.adso.listatareas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.listatareas.dto.HttpGolbalResponseDTO;
import com.adso.listatareas.dto.MessageResponseDTO;
import com.adso.listatareas.dto.StateDTO;
import com.adso.listatareas.dto.StatisticsDTO;
import com.adso.listatareas.dto.TaskCreatDTO;
import com.adso.listatareas.dto.TaskResponseDTO;
import com.adso.listatareas.enums.PriorityTask;
import com.adso.listatareas.enums.StateTask;
import com.adso.listatareas.service.TaskSevice;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskSevice taskSevice;

    @GetMapping("/task")
    public ResponseEntity<HttpGolbalResponseDTO<List<TaskResponseDTO>>> listTask() {
        try {
            HttpGolbalResponseDTO<List<TaskResponseDTO>> reponse = taskSevice.listTask();
            return ResponseEntity.status(HttpStatus.OK).body(reponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("tasks/{id}")
    public ResponseEntity<HttpGolbalResponseDTO<TaskResponseDTO>> getTask(@PathVariable Long id) {
        try {
            HttpGolbalResponseDTO<TaskResponseDTO> reponse = taskSevice.getTask(id);
            return ResponseEntity.status(HttpStatus.OK).body(reponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @PostMapping("create-task")
    public ResponseEntity<HttpGolbalResponseDTO<TaskCreatDTO>> createTask(@Valid@RequestBody TaskCreatDTO taskCreatDTO) {
        try {
            HttpGolbalResponseDTO<TaskCreatDTO> response = taskSevice.createTask(taskCreatDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @PutMapping("update-task/{id}")
    public ResponseEntity<HttpGolbalResponseDTO<TaskResponseDTO>> putTask(@Valid@PathVariable Long id, @RequestBody TaskCreatDTO taskCreatDTO) {
        try {
            HttpGolbalResponseDTO<TaskResponseDTO> response = taskSevice.putTask(id,taskCreatDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PatchMapping("update-state-task/{id}")
    public ResponseEntity<HttpGolbalResponseDTO<TaskResponseDTO>> putSate(@Valid@PathVariable Long id, @RequestBody StateDTO stateDTO){
        try {
            HttpGolbalResponseDTO<TaskResponseDTO> response = taskSevice.putSate(id,stateDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("dele-task/{id}")
    public ResponseEntity<HttpGolbalResponseDTO<MessageResponseDTO>> deleteTask(@PathVariable Long id){
        try {
            HttpGolbalResponseDTO<MessageResponseDTO> response = taskSevice.deleteTask(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/filter-state")
    public ResponseEntity<HttpGolbalResponseDTO<List<TaskResponseDTO>>> serchSatate(@RequestParam StateTask state) {
        try {
            HttpGolbalResponseDTO<List<TaskResponseDTO>> response = taskSevice.serchSatate(state);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
    
    @GetMapping("filter-priority")
    public ResponseEntity<HttpGolbalResponseDTO<List<TaskResponseDTO>>> serchPriority(@RequestParam PriorityTask priorityTask) {
        try {
            HttpGolbalResponseDTO<List<TaskResponseDTO>> response = taskSevice.serchPriority(priorityTask);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
    
    @GetMapping("/serch-title")
    public ResponseEntity<HttpGolbalResponseDTO<TaskResponseDTO>> serchTitle(@RequestParam String title) {
        try {
            HttpGolbalResponseDTO<TaskResponseDTO> response = taskSevice.serchTitle(title);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("/statistics")
    public ResponseEntity<HttpGolbalResponseDTO<StatisticsDTO>> getStatisc() {
        try {
            HttpGolbalResponseDTO<StatisticsDTO> response = taskSevice.getStatisc();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
}
