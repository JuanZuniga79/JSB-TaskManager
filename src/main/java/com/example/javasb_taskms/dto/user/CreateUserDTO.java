package com.example.javasb_taskms.dto.user;

import lombok.Data;

@Data
public class CreateUserDTO {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
}
