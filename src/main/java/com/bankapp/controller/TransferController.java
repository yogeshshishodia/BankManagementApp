package com.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.service.AccountService;
import com.bankapp.service.TransferServiceImpl;


@RestController
public class TransferController {
	
	@Autowired
	private AccountService accountService;
	
	
    @Autowired
    private TransferServiceImpl transferService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestParam String senderUsername,
                                               @RequestParam String receiverUsername,
                                               @RequestParam Integer money) {
        String response = transferService.transferMoney(senderUsername, receiverUsername, money);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
