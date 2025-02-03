package com.example.javasb_taskms.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "Tasks")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public String title;
    @Column(nullable = true)
    public String description;
    public Integer value;
    @ManyToOne
    @JoinColumn(name = "statuses_id")
    public Status status;
    @ManyToOne
    @JoinColumn(name = "users_id")
    public User owner;
}
