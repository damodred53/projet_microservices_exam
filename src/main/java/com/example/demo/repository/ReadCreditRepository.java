package com.example.demo.repository;

import com.example.demo.entity.ReadCredit;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadCreditRepository extends JpaRepository<ReadCredit, Long> {
    Optional<ReadCredit> findByUserUuid(String userUuid);

}
