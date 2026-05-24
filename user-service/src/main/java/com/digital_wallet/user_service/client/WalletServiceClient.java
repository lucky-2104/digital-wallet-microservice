package com.digital_wallet.user_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.digital_wallet.user_service.dto.CreateWalletRequest;

@Repository
@FeignClient(name = "wallet-service")
public interface WalletServiceClient {
	
	@PostMapping("/api/wallet/create")
	public void createWallet(@RequestBody CreateWalletRequest request);
	

}
