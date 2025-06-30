package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Licence;

public interface ReadLicenceRepository extends JpaRepository<Licence, Long> {
    Optional<List<Licence>> findAllByUserUuid(String userUuid);
}
