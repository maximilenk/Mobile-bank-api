package com.ilenkov.bank.walletservice.repository;

import com.ilenkov.bank.walletservice.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findAllByOwnerId(Long ownerId);
}
