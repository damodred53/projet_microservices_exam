package com.example.demo.message;

import lombok.Data;
import java.io.Serializable;

@Data
public class CreditMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String userUuid;
    private int soldeEuros;
}
