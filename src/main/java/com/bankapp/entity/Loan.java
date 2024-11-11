package com.bankapp.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long accountId; // Link the loan to a specific account
    private double loanAmount;
    private double remainingBalance;
    private double interestRate;
    private int tenure; // In months or years
    private LocalDate startDate;
    private LocalDate nextDueDate;

    @Enumerated(EnumType.STRING) // Ensures the enum is stored as a string in the database
    private LoanStatus status; // e.g. APPROVED, PENDING, DENIED

    // Constructors, Getters, and Setters
}
