package com.adso.listatareas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adso.listatareas.dto.HttpGolbalResponseDTO;
import com.adso.listatareas.dto.MessageResponseDTO;
import com.adso.listatareas.dto.StateDTO;
import com.adso.listatareas.dto.StatisticsDTO;
import com.adso.listatareas.dto.TaskCreatDTO;
import com.adso.listatareas.dto.TaskResponseDTO;
import com.adso.listatareas.entity.Task;
import com.adso.listatareas.enums.PriorityTask;
import com.adso.listatareas.enums.StateTask;
import com.adso.listatareas.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskSevice {
    
    private final TaskRepository taskRepository;

    public HttpGolbalResponseDTO<List<TaskResponseDTO>> listTask() {
        HttpGolbalResponseDTO<List<TaskResponseDTO>> response = new HttpGolbalResponseDTO<>();

        List<Task> lTasksFount = taskRepository.findAll();
        if (lTasksFount.isEmpty()) {
            response.setMessage("lista de tareas vacia");
            response.setSuccess(false);
            response.setData(null);
            return response;
        }
        List<TaskResponseDTO> responseList = new ArrayList<>();

        for (Task taskFoun : lTasksFount) {
            TaskResponseDTO taskResponseDTO = new TaskResponseDTO();

            taskResponseDTO.setTitle(taskFoun.getTitle());
            taskResponseDTO.setDescription(taskFoun.getDescription());
            taskResponseDTO.setState(taskFoun.getState());
            taskResponseDTO.setPriority(taskFoun.getPriority());
            taskResponseDTO.setCreationdate(taskFoun.getCreationdate());
            taskResponseDTO.setDateOfUptade(taskFoun.getDateOfUptade());

            responseList.add(taskResponseDTO);
        }

        response.setData(responseList);
        response.setSuccess(true);
        response.setMessage("su lista");


        return response;
    }

    public HttpGolbalResponseDTO<TaskResponseDTO> getTask(Long id) {
        HttpGolbalResponseDTO<TaskResponseDTO> response = new HttpGolbalResponseDTO<>();

        Optional<Task> task = taskRepository.findById(id);

        if (task.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("no existe esa tarea");
            return response;
        }

        Task taskFount = task.get();

        TaskResponseDTO responseTask = new TaskResponseDTO();
        responseTask.setTitle(taskFount.getTitle());
        responseTask.setDescription(taskFount.getDescription());
        responseTask.setState(taskFount.getState());
        responseTask.setPriority(taskFount.getPriority());
        responseTask.setCreationdate(taskFount.getCreationdate());
        responseTask.setDateOfUptade(taskFount.getDateOfUptade());

        response.setMessage("la tarea es: ");
        response.setData(responseTask);
        response.setSuccess(true);


        return response;
    }

    public HttpGolbalResponseDTO<TaskCreatDTO> createTask(TaskCreatDTO taskCreatDTO) {
        HttpGolbalResponseDTO<TaskCreatDTO> response = new HttpGolbalResponseDTO<>();

        Optional<Task> task = taskRepository.findByTitle(taskCreatDTO.getTitle());

        if (!task.isEmpty()) {
            response.setMessage("ya existe esa tarea");
            return response;
        }
        if(taskCreatDTO.getTitle().length()<=3||taskCreatDTO.getTitle()==null){
            response.setMessage("el titutilo no puede estar vacio ni ser menor a tres letras");
            response.setSuccess(false);
            return response;
        }
        Task taskFinal = new Task();
        taskFinal.setTitle(taskCreatDTO.getTitle());
        taskFinal.setDescription(taskCreatDTO.getDescription());
        taskFinal.setPriority(taskCreatDTO.getPriority());
        taskFinal.setState(StateTask.PENDIENTE);
        taskRepository.save(taskFinal);

        response.setMessage("se creo la tarea");
        response.setData(taskCreatDTO);
        
        return response;
    }

    public HttpGolbalResponseDTO<TaskResponseDTO> putTask(Long id, TaskCreatDTO taskCreatDTO) {
        HttpGolbalResponseDTO<TaskResponseDTO> response = new HttpGolbalResponseDTO<>();

        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            response.setMessage("no existe esa tarea");
            response.setSuccess(false);
            return response;
        }

        Task taskFount = task.get();
        if (taskFount.getState()==StateTask.CANCELADA||(taskFount.getState()==StateTask.COMPLETADA)) {
            response.setMessage("las tareas cancelasdas o completadas no se puden mover sopenco");
            response.setSuccess(false);
            return response;
        }
        taskFount.setTitle(taskCreatDTO.getTitle());
        taskFount.setDescription(taskCreatDTO.getDescription());
        taskFount.setState(taskCreatDTO.getStateTask());
        taskFount.setPriority(taskCreatDTO.getPriority());
        taskRepository.save(taskFount);

        TaskResponseDTO taskFinal = new TaskResponseDTO();
        taskFinal.setTitle(taskFount.getTitle());
        taskFinal.setDescription(taskFount.getDescription());
        taskFinal.setState(taskFount.getState());
        taskFinal.setPriority(taskFount.getPriority());
        taskFinal.setCreationdate(taskFount.getCreationdate());
        taskFinal.setDateOfUptade(taskFount.getDateOfUptade());

        response.setMessage("actualizacion completada");
        response.setData(taskFinal);
        response.setSuccess(true);

        return response;
    }

    public HttpGolbalResponseDTO<TaskResponseDTO> putSate(Long id, StateDTO stateDTO) {
        HttpGolbalResponseDTO<TaskResponseDTO> response = new HttpGolbalResponseDTO<>();

        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            response.setMessage("tarea no encontrada");
            response.setSuccess(false);
            return response;
        }
        Task taskFount = task.get();
        if (taskFount.getState()==StateTask.CANCELADA||(taskFount.getState()==StateTask.COMPLETADA)) {
            response.setMessage("las tareas cancelasdas o completadas no se puden mover sopenco");
            response.setSuccess(false);
            return response;
        }
        taskFount.setState(stateDTO.getStateTask());
        taskRepository.save(taskFount);

        TaskResponseDTO taskFinal = new TaskResponseDTO();
        taskFinal.setTitle(taskFount.getTitle());
        taskFinal.setDescription(taskFount.getDescription());
        taskFinal.setState(taskFount.getState());
        taskFinal.setPriority(taskFount.getPriority());
        taskFinal.setCreationdate(taskFount.getCreationdate());
        taskFinal.setDateOfUptade(taskFount.getDateOfUptade());

        response.setMessage("Se cambio el estado con exito");
        response.setData(taskFinal);
        response.setSuccess(true);

        return response;
    }

    public HttpGolbalResponseDTO<MessageResponseDTO> deleteTask(Long id) {
        HttpGolbalResponseDTO<MessageResponseDTO> response = new HttpGolbalResponseDTO<>();
        MessageResponseDTO mesaageResponse = new MessageResponseDTO();
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            response.setMessage("no se encontro esa tarea");
            response.setSuccess(false);
            return response;
        }
        Task taskFount = task.get();
        mesaageResponse.setMessage("la tarea: " + taskFount.getTitle());
        taskRepository.deleteById(id);

        response.setMessage("se elimino ");
        response.setData(mesaageResponse);
        response.setSuccess(true);


        return response;
    }

    public HttpGolbalResponseDTO<List<TaskResponseDTO>> serchSatate(StateTask state) {
        HttpGolbalResponseDTO<List<TaskResponseDTO>> response = new HttpGolbalResponseDTO<>();
        List<Task> listTask = taskRepository.findByState(state);
        List<TaskResponseDTO> listFinal = new ArrayList<>();
        if (listTask.isEmpty()) {
            response.setMessage("no hay taeras");
            response.setSuccess(false);
            return response;
        }

        for (Task listFount : listTask) {
            TaskResponseDTO taskResponse = new TaskResponseDTO();
            taskResponse.setTitle(listFount.getTitle());
            taskResponse.setDescription(listFount.getDescription());
            taskResponse.setState(listFount.getState());
            taskResponse.setPriority(listFount.getPriority());
            taskResponse.setCreationdate(listFount.getCreationdate());
            taskResponse.setDateOfUptade(listFount.getDateOfUptade());
            listFinal.add(taskResponse);
        }
        response.setData(listFinal);
        response.setMessage("filtro "+ state);
        response.setSuccess(true);


        return response;
    }

    public HttpGolbalResponseDTO<List<TaskResponseDTO>> serchPriority(PriorityTask priorityTask) {
        HttpGolbalResponseDTO<List<TaskResponseDTO>> response = new HttpGolbalResponseDTO<>();
        List<Task> listTask = taskRepository.findByPriority(priorityTask);
        List<TaskResponseDTO> listFinal = new ArrayList<>();
        if (listTask.isEmpty()) {
            response.setMessage("no hay taeras");
            response.setSuccess(false);
            return response;
        }

        for (Task listFount : listTask) {
            TaskResponseDTO taskResponse = new TaskResponseDTO();
            taskResponse.setTitle(listFount.getTitle());
            taskResponse.setDescription(listFount.getDescription());
            taskResponse.setState(listFount.getState());
            taskResponse.setPriority(listFount.getPriority());
            taskResponse.setCreationdate(listFount.getCreationdate());
            taskResponse.setDateOfUptade(listFount.getDateOfUptade());
            listFinal.add(taskResponse);
        }
        response.setData(listFinal);
        response.setMessage("filtro "+ priorityTask);
        response.setSuccess(true);


        return response;
    }

    public HttpGolbalResponseDTO<TaskResponseDTO> serchTitle(String title) {
        HttpGolbalResponseDTO<TaskResponseDTO> response = new HttpGolbalResponseDTO<>();
        Optional<Task> task = taskRepository.findByTitle(title);

        if (task.isEmpty()) {
            response.setMessage("no hay tareas llamadas "+title);
            response.setSuccess(false);
            return response;
        }

        Task taskFount = task.get();
        TaskResponseDTO taskFinal = new TaskResponseDTO();
        taskFinal.setTitle(taskFount.getTitle());
        taskFinal.setDescription(taskFount.getDescription());
        taskFinal.setState(taskFount.getState());
        taskFinal.setPriority(taskFount.getPriority());
        taskFinal.setCreationdate(taskFount.getCreationdate());
        taskFinal.setDateOfUptade(taskFount.getDateOfUptade());

        response.setMessage("su tarea: ");
        response.setData(taskFinal);
        response.setSuccess(true);

        return response;
    }

    public HttpGolbalResponseDTO<StatisticsDTO> getStatisc() {
        HttpGolbalResponseDTO<StatisticsDTO> response = new HttpGolbalResponseDTO<>();
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        List<Task> tasks = taskRepository.findAll();
        
        if (tasks.isEmpty()) {
            response.setMessage("no hay tareas diponibles ");
            response.setSuccess(false);
            return response;
        }

    for (Task task : tasks) {
        if (task.getState() == StateTask.PENDIENTE) {
            statisticsDTO.setEarrings(statisticsDTO.getEarrings()+1);
        } else if (task.getState() == StateTask.EN_PROGRESO) {
            statisticsDTO.setInProgress(statisticsDTO.getInProgress()+1);
        } else if (task.getState() == StateTask.COMPLETADA) {
            statisticsDTO.setCompleted(statisticsDTO.getCompleted()+1);
        } else if (task.getState() == StateTask.CANCELADA) {
            statisticsDTO.setCancelled(statisticsDTO.getCancelled()+1);
        }
    }
    statisticsDTO.setAll(statisticsDTO.getAll()+statisticsDTO.getEarrings()+statisticsDTO.getInProgress()+statisticsDTO.getCompleted()+statisticsDTO.getCancelled());

    response.setMessage("el total de tareas: ");
    response.setData(statisticsDTO);
    response.setSuccess(true);
        return response;
    }
}
