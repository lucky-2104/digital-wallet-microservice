package com.digital_wallet.wallet_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digital_wallet.wallet_service.dto.CreateWalletRequest;
import com.digital_wallet.wallet_service.dto.WalletRequestDTO;
import com.digital_wallet.wallet_service.model.Wallet;
import com.digital_wallet.wallet_service.service.WalletService;



@RestController
@RequestMapping("/api/wallet")
public class WalletController {
	
private WalletService walletService;
	
	@Autowired
	public WalletController(WalletService walletService) {
	    this.walletService = walletService;
	}
	//For creating the user
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Wallet create(@RequestBody CreateWalletRequest request) {
		
		System.out.println("This is the user id passed : " + request.getUserId());
		return walletService.createWallet(request.getUserId());
		
	}
	
	
	@PostMapping("/deposit")
	@ResponseStatus(HttpStatus.OK)
	public Wallet depositMoney(@RequestBody WalletRequestDTO request) {
		
		Wallet walletDeposit = walletService.deposit(request);
		return walletDeposit;
	}
	
	@PostMapping("/withdraw")
	@ResponseStatus(HttpStatus.OK)
	public Wallet withdraw(@RequestBody WalletRequestDTO request) {
		
		Wallet walletWithdraw = walletService.withdraw(request);
		return walletWithdraw;
	}
	
	
	@PostMapping("/transfer")
	@ResponseStatus(HttpStatus.OK)
	public Wallet transfer(@RequestBody WalletRequestDTO request) {
		
		Wallet walletSender = walletService.transfer(request);
		return walletSender;
	}
	
	@GetMapping("/{userId}")
	public Wallet getWallet(@PathVariable Long userId) {
		
		return walletService.getWalletByUserId(userId);
	}
	
	
}

