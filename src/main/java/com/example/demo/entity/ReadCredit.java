package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ReadCredit {

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String userUuid;

    @Column(nullable = false)
    private int soldeEuros;

}