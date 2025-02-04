package com.example.javasb_taskms.repositories;

import com.example.javasb_taskms.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByName(String name);
}
