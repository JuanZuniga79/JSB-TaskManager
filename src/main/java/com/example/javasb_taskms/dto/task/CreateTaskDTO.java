package com.example.javasb_taskms.dto.task;

import lombok.Data;

@Data
public class CreateTaskDTO {
    public String title;
    public String description;
    public Integer value;
}
