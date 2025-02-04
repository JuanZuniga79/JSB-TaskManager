package com.example.javasb_taskms.services;

import com.example.javasb_taskms.dto.user.CreateUserDTO;
import com.example.javasb_taskms.dto.user.ResponseUserDTO;
import com.example.javasb_taskms.dto.user.UserCredentialsDTO;
import com.example.javasb_taskms.entities.User;
import com.example.javasb_taskms.mapper.UserMapper;
import com.example.javasb_taskms.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    private final UserRepository _userRepository;
    private final SecurityServices _securityServices;

    public UserServices(UserRepository userRepository, SecurityServices securityServices) {
        _userRepository = userRepository;
        _securityServices = securityServices;
    }

    public ResponseUserDTO login(UserCredentialsDTO credentials) throws Exception {
        User emailUser = _userRepository.findByEmail(credentials.getEmail());
        if (emailUser == null) {
            throw new Exception("Email don't exists");
        }
        boolean isValid = _securityServices.validatePassword(credentials.getPassword(), emailUser.getPassword());
        if(!isValid) {
            throw new Exception("Invalid password");
        }
        String token = _securityServices.generateToken(emailUser.getUsername());
        if(token == null) {
            throw new Exception("Error generating token");
        }
        return UserMapper.UserToResponseDTO(emailUser, token);
    }

    public User getUserByToken(String token) throws Exception {
        String username = _securityServices.getTokenUsername(token);
        User user = _userRepository.findByUsername(username);
        if(user == null) {
            throw new Exception("User not found");
        }
        return user;
    }

    public void createUser(CreateUserDTO user) throws Exception {
        User dbUser = _userRepository.findByEmail(user.getEmail());
        if(dbUser != null) throw new Exception("User already exists");
        user.setPassword(_securityServices.encryptPassword(user.getPassword()));
        User userToSave = UserMapper.CreateUserDTOtoUser(user);
        try{
            _userRepository.save(userToSave);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
