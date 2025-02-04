package com.example.javasb_taskms.controllers;

import com.example.javasb_taskms.dto.task.CreateTaskDTO;
import com.example.javasb_taskms.models.Response;
import com.example.javasb_taskms.services.TaskServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/task")
public class TaskController {

    private final TaskServices _taskServices;

    public TaskController(TaskServices taskServices) {
        this._taskServices = taskServices;
    }

    @PostMapping
    public Mono<ResponseEntity<Response<Void>>> create(
            @RequestBody CreateTaskDTO task,
            @RequestHeader("Authorization") String authHeader
    ) {
        if(task.title == null || task.title.isEmpty()) {
            return Mono.just(ResponseEntity.badRequest().body(
                    new Response.Builder<Void>().message("Title cannot be empty").build()
            ));
        }
        if(task.value == null || task.value == 0){
            return Mono.just(ResponseEntity.badRequest().body(
                    new Response.Builder<Void>().message("Value cannot be empty or zero").build()
            ));
        }
        try{
            _taskServices.createTask(task, authHeader);
            return Mono.just(ResponseEntity.ok().body(
                    new Response.Builder<Void>().message("Task created").code(201).build()
            ));
        }catch (Exception e){
            return Mono.just(ResponseEntity.badRequest().body(
                new Response.Builder<Void>().message(e.getMessage()).build()
            ));
        }
    }

}
