package com.example.javasb_taskms.components;

import com.example.javasb_taskms.entities.User;
import com.example.javasb_taskms.models.BearerToken;
import com.example.javasb_taskms.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticatorManager implements ReactiveAuthenticationManager {

    private final JwtSupport jwtSupport;
    private final UserRepository userRepository;

    public JwtAuthenticatorManager(JwtSupport jwtSupport, UserRepository userRepository) {
        this.jwtSupport = jwtSupport;
        this.userRepository = userRepository;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(auth -> auth instanceof BearerToken)
                .cast(BearerToken.class)
                .flatMap(jwt -> Mono.justOrEmpty(validateToken(jwt)))
                .onErrorMap(error -> {
                    if(error instanceof ResponseStatusException) {
                        return (ResponseStatusException) error;
                    }else{
                        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, error.getMessage());
                    }
                });
    }

    private Authentication validateToken(BearerToken token) {
        String username = jwtSupport.getUsername(token);
        if(username == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        User user = userRepository.findByUsername(username);
        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        if(jwtSupport.isTokenUnexpired(token)) {
            List<SimpleGrantedAuthority> authorities =
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(user.username, null, authorities);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token expired");
    }
}
