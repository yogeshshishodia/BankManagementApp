package com.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.entity.Loan;
import com.bankapp.entity.LoanDetailsResponse;
import com.bankapp.entity.LoanRequest;
import com.bankapp.entity.RepaymentRequest;
import com.bankapp.service.LoanService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<Loan> applyForLoan(@RequestBody LoanRequest loanRequest) {
        Loan loan = loanService.applyForLoan(loanRequest.getAccountId(), loanRequest.getLoanAmount(), 
                                             loanRequest.getInterestRate(), loanRequest.getTenure());
        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }

    @PostMapping("/{loanId}/approve")
    public ResponseEntity<Loan> approveLoan(@PathVariable Long loanId) {
        Loan loan = loanService.approveLoan(loanId);
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    @PostMapping("/{loanId}/repay")
    public ResponseEntity<String> repayLoan(@PathVariable Long loanId, @RequestBody RepaymentRequest repaymentRequest) {
        loanService.repayLoan(loanId, repaymentRequest.getAmount());
        return new ResponseEntity<>("Repayment successful", HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Loan>> getLoansForAccount(@PathVariable Long accountId) {
        List<Loan> loans = loanService.getLoansForAccount(accountId);
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
    
    @GetMapping("/{loanId}/details")
    public LoanDetailsResponse getLoanWithAccountHolder(@PathVariable Long loanId) {
        return loanService.getLoanWithAccountHolder(loanId);
    }
}
