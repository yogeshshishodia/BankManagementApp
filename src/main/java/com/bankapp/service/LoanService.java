package com.bankapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.entity.Account;
import com.bankapp.entity.Loan;
import com.bankapp.entity.LoanDetailsResponse;
import com.bankapp.entity.LoanStatus;
import com.bankapp.repository.LoanRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AccountService accountService;

    public Loan applyForLoan(Long accountId, double amount, double interestRate, int tenure) {
        Loan loan = new Loan();
        loan.setAccountId(accountId);
        loan.setLoanAmount(amount);
        loan.setRemainingBalance(amount);
        loan.setInterestRate(interestRate);
        loan.setTenure(tenure);
        loan.setStartDate(LocalDate.now());
        loan.setNextDueDate(LocalDate.now().plusMonths(1)); // Example for monthly payments
        loan.setStatus(LoanStatus.PENDING);
        return loanRepository.save(loan);
    }

    public Loan approveLoan(Long loanId) {
        // Fetch loan by ID
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new EntityNotFoundException("Loan not found"));
        
        // Ensure that accountId in the loan exists in the Account table
        Account account = accountService.getByAccountNumber(loan.getAccountId());
        if (account == null) {
            throw new EntityNotFoundException("Account not found");
        }

        // Set loan status to APPROVED
        loan.setStatus(LoanStatus.APPROVED);
        
        // Disburse the loan amount to the account
        accountService.deposit(loan.getAccountId(), loan.getLoanAmount());

        // Save the loan with the updated status
        return loanRepository.save(loan);
    }


    public void repayLoan(Long loanId, double paymentAmount) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new EntityNotFoundException("Loan not found"));
        loan.setRemainingBalance(loan.getRemainingBalance() - paymentAmount);
        loan.setNextDueDate(LocalDate.now().plusMonths(1)); // Update next due date
        loanRepository.save(loan);
        // Deduct payment from account
        accountService.withdraw(loan.getAccountId(), paymentAmount);
    }

    public double calculateInterest(Loan loan) {
        return loan.getRemainingBalance() * loan.getInterestRate() / 100;
    }

    public List<Loan> getLoansForAccount(Long accountId) {

        return loanRepository.findByAccountId(accountId);
    }
    
    public LoanDetailsResponse getLoanWithAccountHolder(Long loanId) {
        // Fetch loan by ID
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found"));

        // Fetch associated account using accountId from loan
        Account account = accountService.getByAccountNumber(loan.getAccountId());

        // Create a custom response object combining loan and account holder details
        LoanDetailsResponse response = new LoanDetailsResponse();
        response.setLoan(loan);
        response.setAccountHolderName(account.getAccountHolderName());
        response.setAccountNumber(account.getAccountNumber());

        return response;
    }
    
    
}
