package com.example.demo.repository;

import com.example.demo.entity.Credit;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    Optional<Credit> findByUserUuid(String userUuid);

}
