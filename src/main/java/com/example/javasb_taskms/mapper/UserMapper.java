package com.example.javasb_taskms.mapper;

import com.example.javasb_taskms.dto.user.CreateUserDTO;
import com.example.javasb_taskms.dto.user.ResponseUserDTO;
import com.example.javasb_taskms.entities.User;
import com.example.javasb_taskms.utils.HexUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User CreateUserDTOtoUser(CreateUserDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUsername(dto.firstName.concat(HexUtils.generateHexString(6)));
        return user;
    }

    public static ResponseUserDTO UserToResponseDTO(User user, String token) {
        ResponseUserDTO dto = new ResponseUserDTO();
        dto.id = user.getId();
        dto.firstName = user.getFirstName();
        dto.lastName = user.getLastName();
        dto.email = user.getEmail();
        dto.username = user.getUsername();
        dto.token = token;
        return dto;
    }

}
