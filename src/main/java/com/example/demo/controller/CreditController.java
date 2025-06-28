package com.example.demo.controller;

import com.example.demo.entity.Credit;
import com.example.demo.repository.CreditRepository;
import com.example.demo.request.CreditRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credits")
public class CreditController {

  private final CreditRepository repo;

  public CreditController(CreditRepository repo) {
    this.repo = repo;
  }

  @PostMapping("/{userUuid}")
  public ResponseEntity<Credit> setCredit(
      @PathVariable String userUuid,
      @RequestBody CreditRequest creditRequest) {
    Credit credit = repo.findByUserUuid(userUuid)
        .orElse(new Credit());
    credit.setUserUuid(userUuid);
    credit.setSoldeEuros(creditRequest.getAmount());
    return ResponseEntity.ok(repo.save(credit));
  }

  @GetMapping("/{userUuid}")
  public ResponseEntity<Credit> getCredit(@PathVariable String userUuid) {
    return repo.findByUserUuid(userUuid)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{userUuid}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteByUserUuid(@PathVariable String userUuid) {
    repo.findByUserUuid(userUuid)
        .ifPresentOrElse(
            credit -> repo.delete(credit),
            () -> {
              throw new RuntimeException("Credit non trouvé pour userUuid: " + userUuid);
            });
  }

}