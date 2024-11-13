package com.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.entity.Account;
import com.bankapp.service.AccountService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/create")
	public Account create(@RequestBody Account account) {
		Account ac=accountService.create(account);
		return ac;
		
	}
	
	@GetMapping("/{id}")
	public Account findByAccountNumbr(@PathVariable ("id") Long accountNumber) {
		Account account=accountService.getByAccountNumber(accountNumber);
		return account;
		
	}
	
	@CrossOrigin("*")
	@PutMapping("/deposit/{accountNumber}/{amount}")
	public  Account deposit(@PathVariable Long accountNumber, @PathVariable Double amount) {
		Account acc=accountService.deposit(accountNumber, amount);
		return acc;
		
	}
	
	@PutMapping("/withdraw/{accountNumber}/{amount}")
	public  Account withdraw(@PathVariable Long accountNumber, @PathVariable Double amount) {
		Account acc=accountService.withdraw(accountNumber, amount);
		return acc;
		
	}
	
	@CrossOrigin("*")
	@GetMapping("/getall")
	public List<Account>getall(){
		return accountService.getAllAccountDetails();
		
	}

}
