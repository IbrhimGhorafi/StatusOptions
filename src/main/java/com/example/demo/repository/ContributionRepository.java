package com.example.demo.repository;

import com.example.demo.entity.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {
     Contribution findByEmail(String email);
     Optional<Contribution> findById(Long id);
}

