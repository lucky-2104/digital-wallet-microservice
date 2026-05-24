package com.digital_wallet.wallet_service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="transactions")
public class Transaction {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(precision=16,scale=4)
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	
	private String description;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	
	@ManyToOne
	@JoinColumn(name="wallet_id")
	private Wallet wallet;
	
	
	//Constructor
	
	public Transaction(){}


	public Transaction(Long id, BigDecimal amount, TransactionType transactionType, String description,
			LocalDateTime createdAt, Wallet wallet) {
		super();
		this.id = id;
		this.amount = amount;
		this.transactionType = transactionType;
		this.description = description;
		this.createdAt = createdAt;
		this.wallet = wallet;
	}

	//Getter & Setter

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public TransactionType getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public Wallet getWallet() {
		return wallet;
	}


	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}


	
	
	

}
