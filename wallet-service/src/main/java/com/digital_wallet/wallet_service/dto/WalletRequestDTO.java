package com.digital_wallet.wallet_service.dto;

import java.math.BigDecimal;

public class WalletRequestDTO {
	
	
	private Long userId;
	
	private BigDecimal amount;
	
	private String description;
	
	private Long targetUserId;
	
	
	
	//Constructor
	public WalletRequestDTO() {}


	public WalletRequestDTO(Long userId, BigDecimal amount, String description, Long targetUserId) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.description = description;
		this.targetUserId = targetUserId;
	}


	
	
	//Getter & Setter
	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Long getTargetUserId() {
		return targetUserId;
	}


	public void setTargetUserId(Long targetUserId) {
		this.targetUserId = targetUserId;
	}
	

	
	
	

}
