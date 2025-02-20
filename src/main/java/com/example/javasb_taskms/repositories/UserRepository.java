package com.example.javasb_taskms.repositories;

import com.example.javasb_taskms.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    public User findByUsername(String username);
    public User findByEmail(String email);
}
