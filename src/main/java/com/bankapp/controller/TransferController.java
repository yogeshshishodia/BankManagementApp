package com.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.entity.Transfer;
import com.bankapp.service.AccountService;
import com.bankapp.service.TransferService;
import com.bankapp.service.TransferServiceImpl;


@RestController
public class TransferController {
	
	@Autowired
	private AccountService accountService;
	
	
    @Autowired
    private TransferService transferService;
    

//    @PostMapping("/transfer")
//    public ResponseEntity<String> transferMoney(@RequestParam String senderUsername,
//                                               @RequestParam String receiverUsername,
//                                               @RequestParam Integer money) {
//        String response = transferService.transferMoney(senderUsername, receiverUsername, money);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
     
    
    
//                          you can use any one of them
    
    
    
    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody Transfer transferRequest) {
        String response = transferService.transferMoney(transferRequest.getSenderUserName(), 
                                                       transferRequest.getReceiverUserName(), 
                                                       transferRequest.getMoney());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/get")
    public List<Transfer>getall(){
    	return transferService.getall();
    			
    }

}
