package com.example.javasb_taskms.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "Statuses")
public class Status {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(unique = true)
    public String name;
    @Column(nullable = true)
    public String description;
}
