package com.example.javasb_taskms.controllers.free;

import com.example.javasb_taskms.dto.user.CreateUserDTO;
import com.example.javasb_taskms.dto.user.ResponseUserDTO;
import com.example.javasb_taskms.dto.user.UserCredentialsDTO;
import com.example.javasb_taskms.models.Response;
import com.example.javasb_taskms.services.UserServices;
import com.example.javasb_taskms.utils.fieldUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/public/user")
public class FreeUserController {

    private final UserServices _userServices;

    public FreeUserController(UserServices userServices) {
        this._userServices = userServices;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Response<ResponseUserDTO>>> login(@RequestBody UserCredentialsDTO credentials) {
        System.out.println(credentials);
        if(credentials.email == null || credentials.email.isEmpty()) {
            return Mono.justOrEmpty(ResponseEntity.badRequest().body(
                    new Response.Builder<ResponseUserDTO>().message("Email is required").code(400).build()));
        }
        try{
            fieldUtil.isEmail(credentials.getEmail());
        }catch (Exception e){
            return Mono.justOrEmpty(ResponseEntity.badRequest().body(
                    new Response.Builder<ResponseUserDTO>().message("Invalid email").code(400).build()));
        }
        try{
            ResponseUserDTO user = _userServices.login(credentials);
            return Mono.just(ResponseEntity.ok().body(
                    new Response.Builder<ResponseUserDTO>().message("Login successful").code(200).data(user).build()));
        }catch (Exception e){
            return Mono.just(ResponseEntity.badRequest().body(
                    new Response.Builder<ResponseUserDTO>().message("Login failed").code(404).message(e.getMessage())
                            .build()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody CreateUserDTO user){
        if(user.firstName == null || user.lastName == null){
            return ResponseEntity.badRequest().body("You must enter first name and last name");
        }else if(user.email == null || user.password == null){
            return ResponseEntity.badRequest().body("You must enter email and password");
        }
        try {
            _userServices.createUser(user);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
