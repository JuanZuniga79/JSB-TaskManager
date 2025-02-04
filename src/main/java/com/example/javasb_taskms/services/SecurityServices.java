package com.example.javasb_taskms.services;

import com.example.javasb_taskms.components.JwtSupport;
import com.example.javasb_taskms.models.BearerToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServices {

    public final PasswordEncoder _passwordEncoder;
    public final JwtSupport _jwtSupport;

    public SecurityServices(PasswordEncoder passwordEncoder, JwtSupport jwtSupport) {
        _passwordEncoder = passwordEncoder;
        _jwtSupport = jwtSupport;
    }

    public String generateToken(String username){
        return _jwtSupport.generate(username).getValue();
    }

    public String getTokenUsername(String token){
        String newToken = token.substring(7);
        return _jwtSupport.getUsername(new BearerToken(newToken));
    }

    public String encryptPassword(String password) {
        return _passwordEncoder.encode(password);
    }

    public boolean validatePassword(String password, String encryptedPassword) {
        return _passwordEncoder.matches(password, encryptedPassword);
    }

}
