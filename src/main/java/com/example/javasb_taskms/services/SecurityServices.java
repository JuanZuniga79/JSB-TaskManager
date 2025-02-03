package com.example.javasb_taskms.services;

import com.example.javasb_taskms.components.JwtSupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServices {

    public final PasswordEncoder _passwordEncoder;
    public final JwtSupport _jwtSupport;

    public SecurityServices(PasswordEncoder passwordEncoder, JwtSupport jwtSupport) {
        this._passwordEncoder = passwordEncoder;
        this._jwtSupport = jwtSupport;
    }

    public String generateToken(String username){
        return _jwtSupport.generate(username).getValue();
    }

    public String encryptPassword(String password) {
        return _passwordEncoder.encode(password);
    }

    public boolean validatePassword(String password, String encryptedPassword) {
        return _passwordEncoder.matches(password, encryptedPassword);
    }

}
