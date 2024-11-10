package com.bankapp.service;

import java.util.List;

import com.bankapp.entity.Transfer;

public interface TransferService {
	
	public String transferMoney(String senderUsername, String receiverUsername, Integer money);
	
	public List<Transfer>getall();

}
