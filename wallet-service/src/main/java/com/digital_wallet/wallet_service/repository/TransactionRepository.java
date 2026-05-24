package com.digital_wallet.wallet_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digital_wallet.wallet_service.model.Transaction;
import com.digital_wallet.wallet_service.model.Wallet;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

	List<Transaction> findAllByWallet(Wallet wallet);
}
