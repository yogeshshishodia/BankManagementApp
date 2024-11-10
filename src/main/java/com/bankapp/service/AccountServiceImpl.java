package com.bankapp.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.entity.Account;
import com.bankapp.repository.AccountRepository;


@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account create(Account account) {
		Account acc=accountRepository.save(account);
		return acc;
	}

	@Override
	public Account getByAccountNumber(Long acccountNumber) {
		Optional<Account>acc=accountRepository.findById(acccountNumber);
		if(acc.isEmpty()) {
			throw new RuntimeException("account not find");
		}
		else {
			Account account_found=acc.get();
			return account_found;
		}
		
	}

	@Override
	public Account deposit(Long accountNumber, Double amount) {
		Optional<Account> acc=accountRepository.findById(accountNumber);
		if(acc.isEmpty()) {
			throw new RuntimeException("account not found");
		}else {
			Account account_present=acc.get();
			double ac=account_present.getAmount()+amount;
			account_present.setAmount(ac);
			accountRepository.save(account_present);
			return account_present;
		}
		
		
		
	}

	@Override
	public Account withdraw(Long accountNumber, Double amount) {
	    // Fetch the account using the account number
	    Optional<Account> acc = accountRepository.findById(accountNumber);
	    
	    if (acc.isEmpty()) {
	        // If account is not found, throw an exception
	        throw new RuntimeException("Account not found");
	    } else {
	        Account accountPresent = acc.get();
	        
	        // Check if there are sufficient funds in the account
	        if (accountPresent.getAmount() < amount) {
	            // If insufficient funds, throw an exception
	            throw new RuntimeException("Insufficient funds");
	        } else {
	            // Perform the withdrawal if enough funds are available
	            double newBalance = accountPresent.getAmount() - amount;
	            accountPresent.setAmount(newBalance);
	            
	            // Save the updated account object
	            accountRepository.save(accountPresent);
	            return accountPresent;
	        }
	    }
	}

	@Override
	public List<Account> getAllAccountDetails() {
		
		return accountRepository.findAll();
	}

//	@Override
//	public Account transfer(Long accountName, Double amount) {
//		Optional<Account> acc=accountRepository.findById(accountName);
//		if(acc.isEmpty()) {
//			throw new RuntimeException("account not found");
//		}else {
//			Account account_present=acc.get();
//			double ac=account_present.getAmount()-amount;
//			account_present.setAmount(ac);
//			accountRepository.save(account_present);
//			return account_present;
//		}
		
		
//	}
    public void updateBalance(Account account, Double newAmount) {
        account.setAmount(newAmount);
        accountRepository.save(account);


}

	@Override
	public Account findByAccountHolderName(String accountHolderName) {
		return accountRepository.findByAccountHolderName(accountHolderName);
	}
}
