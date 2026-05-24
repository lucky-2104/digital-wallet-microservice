package com.digital_wallet.wallet_service.dto;

public class CreateWalletRequest {
	
	private Long userId;
	
	public CreateWalletRequest() {}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public CreateWalletRequest(Long userId) {
		super();
		this.userId = userId;
	}
	
	
}
