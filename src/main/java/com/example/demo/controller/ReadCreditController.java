package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ReadCredit;
import com.example.demo.repository.ReadCreditRepository;

@RestController
@RequestMapping("/read-credits")
public class ReadCreditController {

    private final ReadCreditRepository repo;

    public ReadCreditController(ReadCreditRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{userUuid}")
    public ResponseEntity<ReadCredit> getCredit(@PathVariable String userUuid) {
        return repo.findByUserUuid(userUuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
