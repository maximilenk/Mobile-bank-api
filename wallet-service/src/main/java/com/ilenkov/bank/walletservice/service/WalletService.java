package com.ilenkov.bank.walletservice.service;

import com.ilenkov.bank.walletservice.entity.Wallet;
import com.ilenkov.bank.walletservice.entity.dto.WalletUpdateBalanceRequest;
import com.ilenkov.bank.walletservice.exceptions.NotEnoughMoneyException;
import com.ilenkov.bank.walletservice.exceptions.OwnerNotFoundException;
import com.ilenkov.bank.walletservice.exceptions.WalletNotFoundException;

import java.util.List;

public interface WalletService {
    List<Wallet> findAllByOwnerId(Long ownerId, String authorizationHeader) throws OwnerNotFoundException;

    Wallet findById(Long walletId) throws WalletNotFoundException;

    void updateBalance(WalletUpdateBalanceRequest balanceUpdate) throws WalletNotFoundException, NotEnoughMoneyException;

    void create(Long ownerId, String authorizationHeader) throws OwnerNotFoundException;

    double getBalanceById(Long walletId) throws WalletNotFoundException;
}
