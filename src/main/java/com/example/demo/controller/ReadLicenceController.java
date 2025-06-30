package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Licence;
import com.example.demo.repository.ReadLicenceRepository;

public class ReadLicenceController {

    private final ReadLicenceRepository repo;

    public ReadLicenceController(ReadLicenceRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{userUuid}")
    public ResponseEntity<List<Licence>> getAllLicencesByUser(@PathVariable String userUuid) {
        Optional<List<Licence>> optionalLicences = repo.findAllByUserUuid(userUuid);
        if (optionalLicences.isEmpty() || optionalLicences.get().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalLicences.get());
    }
}
