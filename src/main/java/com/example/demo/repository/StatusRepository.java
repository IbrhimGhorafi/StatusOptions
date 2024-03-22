package com.example.demo.repository;

import com.example.demo.entity.Status;
import com.example.demo.entity.StatusLibelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface StatusRepository extends JpaRepository<Status,Long> {
    Status findByLibelle(StatusLibelle statusLibelle);
    Optional<Status> findById(Long id);
}
