package com.example.javasb_taskms.mapper;

import com.example.javasb_taskms.dto.task.CreateTaskDTO;
import com.example.javasb_taskms.entities.Status;
import com.example.javasb_taskms.entities.Task;
import com.example.javasb_taskms.entities.User;

public class TaskMapper {

    public static Task fromCreateTaskDTOToEntity(CreateTaskDTO createTaskDTO, User owner, Status status) {
        Task task = new Task();
        task.owner = owner;
        task.title = createTaskDTO.getTitle();
        task.description = createTaskDTO.getDescription();
        task.value = createTaskDTO.getValue();
        task.status = status;
        return task;
    }

}
