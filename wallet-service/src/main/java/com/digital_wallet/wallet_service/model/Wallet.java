package com.digital_wallet.wallet_service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="wallets")
public class Wallet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(precision=19,scale=4)
	private BigDecimal balance;
	
	private String currency;
	
	private String status;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	
	@Column(name="user_id")
	private Long userId;

	//Constructor 
	//All field Constructor
	public Wallet(Long id, BigDecimal balance, String currency, String status, LocalDateTime createdAt, Long userId) {
		super();
		this.id = id;
		this.balance = balance;
		this.currency = currency;
		this.status = status;
		this.createdAt = createdAt;
		this.userId = userId;
	}

	//No args constructor
	public Wallet() {
		
	}
	
	
	//Getter & Setter
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}	
}
