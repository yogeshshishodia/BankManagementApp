package com.bankapp.service;

import java.util.List;

import com.bankapp.entity.Account;

public interface AccountService {
	
	public Account create(Account account);
	
	public Account getByAccountNumber(Long acccountNumber);
	
	public Account findByAccountHolderName(String accountHolderName);
	
	public List<Account>getAllAccountDetails();
	
	public Account deposit(Long accountNumber ,Double amount);
	
	public Account withdraw(Long accountNumber ,Double amount);
	
//	public Account transfer(Long accountName, Double amount);

	public void updateBalance(Account sender, Double amount);

	

}
