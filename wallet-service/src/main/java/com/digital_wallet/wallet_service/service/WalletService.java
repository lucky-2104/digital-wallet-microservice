package com.digital_wallet.wallet_service.service;



import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital_wallet.wallet_service.dto.WalletRequestDTO;
import com.digital_wallet.wallet_service.model.Transaction;
import com.digital_wallet.wallet_service.model.TransactionType;
import com.digital_wallet.wallet_service.model.Wallet;
import com.digital_wallet.wallet_service.repository.TransactionRepository;
import com.digital_wallet.wallet_service.repository.WalletRepository;

import jakarta.transaction.Transactional;


@Service
public class WalletService {
	
	private WalletRepository walletRepo;
	private TransactionRepository transactionRepo;
	
	@Autowired
	public WalletService(WalletRepository walletRepo,TransactionRepository transactionRepo) {
		this.transactionRepo = transactionRepo;
		this.walletRepo = walletRepo;
	}
	
	
	//Business Logic
	
	public Wallet createWallet(Long userId) {
		Wallet newWallet = new Wallet();
		newWallet.setUserId(userId);
		newWallet.setBalance(BigDecimal.ZERO);
		newWallet.setStatus("ACTIVE");
		newWallet.setCurrency("INR");
		
		walletRepo.save(newWallet);
		
		return newWallet;
		
	}
	
	@Transactional
	public Wallet deposit(WalletRequestDTO request) {
		
		Wallet wallet = walletRepo.findByUserId(request.getUserId()).orElseThrow(() -> new RuntimeException("User Not found"));
		
		//Making next two commands atomic will be beneficial.
		wallet.setBalance(wallet.getBalance().add(request.getAmount()));
		walletRepo.save(wallet);
		
		Transaction transactionCreated = new Transaction();
		
		
		transactionCreated.setAmount(request.getAmount());
		transactionCreated.setTransactionType(TransactionType.DEPOSIT);
		transactionCreated.setDescription(request.getDescription());
		transactionCreated.setWallet(wallet);
		transactionRepo.save(transactionCreated);
		return wallet;
		
	}
	
	@Transactional
	public Wallet withdraw(WalletRequestDTO request) {
		
		Wallet wallet = walletRepo.findByUserId(request.getUserId()).orElseThrow(() -> new RuntimeException("User Not found"));
		
		//Adding thread here will be beneficial as And locking it using mutex.
		if(wallet.getBalance().compareTo(request.getAmount()) >= 0) {
			wallet.setBalance(wallet.getBalance().subtract(request.getAmount()));
			walletRepo.save(wallet);
		}
		else {
			throw new RuntimeException("Insufficient Balance");
		}
		
		
		
		Transaction transactionCreated = new Transaction();
		
		
		transactionCreated.setAmount(request.getAmount());
		transactionCreated.setTransactionType(TransactionType.WITHDRAWAL);
		transactionCreated.setDescription(request.getDescription());
		transactionCreated.setWallet(wallet);
		transactionRepo.save(transactionCreated);
		return wallet;
		
	}
	
	@Transactional
	public Wallet transfer(WalletRequestDTO request) {
		
		Wallet senderWallet = walletRepo.findByUserId(request.getUserId()).orElseThrow(()-> new RuntimeException("Sender user not found"));
		Wallet receiverWallet = walletRepo.findByUserId(request.getTargetUserId()).orElseThrow(()-> new RuntimeException("Sender user not found"));
		
		
		if(senderWallet.getBalance().compareTo(request.getAmount()) >= 0) {
			senderWallet.setBalance(senderWallet.getBalance().subtract(request.getAmount()));
			walletRepo.save(senderWallet);
			
			receiverWallet.setBalance(receiverWallet.getBalance().add(request.getAmount()));
			walletRepo.save(receiverWallet);
		}
		else {
			throw new RuntimeException("Insufficient Balance");
		}
		
		Transaction senderTransaction = new Transaction();
		Transaction receiverTransaction = new Transaction();
		
		//Setting transaction for sender 
		senderTransaction.setAmount(request.getAmount());
		senderTransaction.setDescription(request.getDescription());
		senderTransaction.setTransactionType(TransactionType.TRANSFER);
		senderTransaction.setWallet(senderWallet);
		
		//Setting transaction for receiver
		
		receiverTransaction.setAmount(request.getAmount());
		receiverTransaction.setDescription(request.getDescription());
		receiverTransaction.setTransactionType(TransactionType.DEPOSIT);
		receiverTransaction.setWallet(receiverWallet);
		
		transactionRepo.save(senderTransaction);
		transactionRepo.save(receiverTransaction);
		
		
		return senderWallet;
	}
	
	public Wallet getWalletByUserId(Long userId) {
		return walletRepo.findByUserId(userId)
			       .orElseThrow(() -> new RuntimeException("Wallet not found"));
	}
	
	
	
	

}
