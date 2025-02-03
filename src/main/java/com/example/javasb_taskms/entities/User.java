package com.example.javasb_taskms.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "Users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public String firstName;
    public String lastName;
    @Column(unique = true)
    public String email;
    @Column(unique = true)
    public String username;
    @Column(length = 500)
    public String password;
}
