package com.example.demo.controller;

import com.example.demo.entity.Credit;
import com.example.demo.message.CreditMessage;
import com.example.demo.repository.CreditRepository;
import com.example.demo.request.CreditRequest;

import org.springframework.amqp.core.AmqpTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credits")
public class CreditController {

  private final CreditRepository repo;
  private final AmqpTemplate amqpTemplate;

  public CreditController(CreditRepository repo, AmqpTemplate amqpTemplate) {
    this.repo = repo;
    this.amqpTemplate = amqpTemplate;
  }

  @PostMapping("/{userUuid}")
  public ResponseEntity<Credit> setCredit(
      @PathVariable String userUuid,
      @RequestBody CreditRequest creditRequest) {
    Credit credit = repo.findByUserUuid(userUuid)
        .orElse(new Credit());
    credit.setUserUuid(userUuid);
    credit.setSoldeEuros(creditRequest.getAmount());
    Credit saved = repo.save(credit);

    CreditMessage msg = new CreditMessage();
    msg.setId(saved.getId());
    msg.setUserUuid(saved.getUserUuid());
    msg.setSoldeEuros(saved.getSoldeEuros());

    try {
      amqpTemplate.convertAndSend("credit-exchange", "credit.created", msg);

    } catch (Exception e) {

      e.printStackTrace();
      return ResponseEntity.status(500).body(saved);
    }

    return ResponseEntity.ok(saved);

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