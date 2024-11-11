package com.bankapp.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanRequest {
    private Long accountId;
    private double loanAmount;
    private double interestRate;
    private int tenure;
    // Getters and Setters
}


