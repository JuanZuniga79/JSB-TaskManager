package com.example.javasb_taskms.services;

import com.example.javasb_taskms.dto.task.CreateTaskDTO;
import com.example.javasb_taskms.entities.Status;
import com.example.javasb_taskms.entities.Task;
import com.example.javasb_taskms.entities.User;
import com.example.javasb_taskms.mapper.TaskMapper;
import com.example.javasb_taskms.repositories.StatusRepository;
import com.example.javasb_taskms.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskServices {

    private final TaskRepository _taskRepository;
    private final StatusRepository _statusRepository;
    private final UserServices _userServices;

    public TaskServices(TaskRepository taskRepository, UserServices userServices, StatusRepository statusRepository) {
        _taskRepository = taskRepository;
        _userServices = userServices;;
        _statusRepository = statusRepository;
    }

    public void createTask(CreateTaskDTO task, String token) throws Exception {
        User owner;
        try{
            owner = _userServices.getUserByToken(token);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        if(owner == null){
            throw new Exception("User not found");
        }
        Status status;
        status = _statusRepository.findByName("todo");
        if(status == null){
            Status newStatus = new Status();
            newStatus.name = "todo";
            status = _statusRepository.save(newStatus);
        }
        Task entityTask = TaskMapper.fromCreateTaskDTOToEntity(task, owner, status);
        _taskRepository.save(entityTask);
    }

}
