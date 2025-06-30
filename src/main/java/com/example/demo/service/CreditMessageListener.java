package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.entity.ReadCredit;
import com.example.demo.message.CreditMessage;
import com.example.demo.repository.ReadCreditRepository;

@Component
public class CreditMessageListener {

    private final ReadCreditRepository readCreditRepo;

    public CreditMessageListener(ReadCreditRepository readCreditRepo) {
        this.readCreditRepo = readCreditRepo;
    }

    @RabbitListener(queues = "credit-queue")
    public void handleCreditMessage(CreditMessage message) {
        ReadCredit read = new ReadCredit();
        read.setId(message.getId());
        read.setUserUuid(message.getUserUuid());
        read.setSoldeEuros(message.getSoldeEuros());

        readCreditRepo.save(read);
    }
}
