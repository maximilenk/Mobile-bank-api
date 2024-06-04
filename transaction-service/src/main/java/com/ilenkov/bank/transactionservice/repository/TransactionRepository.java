package com.ilenkov.bank.transactionservice.repository;

import com.ilenkov.bank.transactionservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByReceiverWalletId(long walletId);
    List<Transaction> findBySenderWalletId(long walletId);
}
