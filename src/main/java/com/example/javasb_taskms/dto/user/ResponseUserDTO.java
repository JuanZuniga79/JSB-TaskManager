package com.example.javasb_taskms.dto.user;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseUserDTO {
    public UUID id;
    public String firstName;
    public String lastName;
    public String email;
    public String username;
    public String token;
}
