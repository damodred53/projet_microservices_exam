package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Licence;
import com.example.demo.repository.LicenceRepository;
import com.example.demo.request.LicenceRequest;

@RestController
@RequestMapping("/licences")
public class LicenceController {

    private final LicenceRepository repo;

    public LicenceController(LicenceRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Licence> createLicence(@RequestBody LicenceRequest request) {
        Licence licence = new Licence();
        licence.setUserUuid(request.getUserUuid());
        licence.setMovieId(request.getMovieId());
        licence.setDatePurchase(LocalDateTime.now());
        licence.setPrice(request.getPrice());

        return ResponseEntity.ok(repo.save(licence));
    }

}
