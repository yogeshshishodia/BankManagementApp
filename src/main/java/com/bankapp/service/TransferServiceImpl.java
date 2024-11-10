package com.bankapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.entity.Account;
import com.bankapp.entity.Transfer;
import com.bankapp.repository.AccountRepository;
import com.bankapp.repository.TransferRepository;


@Service
public class TransferServiceImpl implements TransferService {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransferRepository transferRepository;
	
	public String transferMoney(String senderUsername, String receiverUsername, Integer money) {
        Optional<Account> senderOpt = Optional.of(accountService.findByAccountHolderName(senderUsername));
        Optional<Account> receiverOpt = Optional.of(accountService.findByAccountHolderName(receiverUsername));

        if (senderOpt.isPresent() && receiverOpt.isPresent()) {
            Account sender = senderOpt.get();
            Account receiver = receiverOpt.get();
            

            if (sender.getAmount() >= money) {
                // Deduct money from sender
                sender.setAmount(sender.getAmount() - money);
                // Add money to receiver
                receiver.setAmount(receiver.getAmount() + money);

                // Update user balances
                accountService.updateBalance(sender, sender.getAmount());
                accountService.updateBalance(receiver, receiver.getAmount());

                // Save transaction
                Transfer transaction = new Transfer();
//                transaction.setSender(sender);
//                transaction.setReceiver(receiver);
                transaction.setMoney(money);
                
                transaction.setReceiverUserName(receiverUsername);
                transaction.setSenderUserName(senderUsername);
                
                transferRepository.save(transaction);

                return "Transaction successful!";
            } else {
                return "Insufficient funds.";
            }
        } else {
            return "Sender or Receiver not found.";
        }
    }

	@Override
	public List<Transfer> getall() {
		
		return transferRepository.findAll();
	}


}
