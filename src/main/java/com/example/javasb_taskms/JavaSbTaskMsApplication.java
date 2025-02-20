package com.example.javasb_taskms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class JavaSbTaskMsApplication {

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(JavaSbTaskMsApplication.class, args);
    }

}
