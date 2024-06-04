package com.ilenkov.bank.walletservice.service;


import com.ilenkov.bank.walletservice.entity.Wallet;
import com.ilenkov.bank.walletservice.entity.dto.WalletUpdateBalanceRequest;
import com.ilenkov.bank.walletservice.exceptions.NotEnoughMoneyException;
import com.ilenkov.bank.walletservice.exceptions.OwnerNotFoundException;
import com.ilenkov.bank.walletservice.exceptions.WalletNotFoundException;
import com.ilenkov.bank.walletservice.feigninterface.UserInterface;
import com.ilenkov.bank.walletservice.repository.WalletRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final UserInterface userInterface;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository,
                             UserInterface userInterface) {
        this.walletRepository = walletRepository;
        this.userInterface = userInterface;
    }

    @Override
    public Wallet findById(Long walletId) throws WalletNotFoundException {
        return walletRepository.findById(walletId)
                .orElseThrow(()->new WalletNotFoundException("Wallet with id " + walletId + " not found"));
    }

    @Override
    public List<Wallet> findAllByOwnerId(Long ownerId, String authorizationHeader) throws OwnerNotFoundException {
        try {
            ResponseEntity<?> response = userInterface.getUserById(authorizationHeader, ownerId);
            return walletRepository.findAllByOwnerId(ownerId);
        } catch (FeignException e) {
            throw new OwnerNotFoundException("Owner with id " + ownerId + " not found");
        }
    }

    @Override
    public void create(Long ownerId, String authorizationHeader) throws OwnerNotFoundException {
        try {
            ResponseEntity<?> response = userInterface.getUserById(authorizationHeader, ownerId);
            Wallet wallet = new Wallet(500L, ownerId);
            walletRepository.save(wallet);
        } catch (FeignException e) {
            throw new OwnerNotFoundException("Owner with id " + ownerId + " not found");
        }
    }

    @Override
    public void updateBalance(WalletUpdateBalanceRequest balanceUpdate) throws WalletNotFoundException,
            NotEnoughMoneyException {
        Wallet wallet = walletRepository.findById(balanceUpdate.getId())
                .orElseThrow(()->new WalletNotFoundException("Wallet with id "
                        + balanceUpdate.getId() + " not found"));
        if (wallet.getBalance() + balanceUpdate.getAmount() < 0) {
            throw new NotEnoughMoneyException("wallet balance is lower than amount of transfer");
        } else {
            wallet.setBalance(wallet.getBalance() + balanceUpdate.getAmount());
            walletRepository.save(wallet);
        }
    }

    @Override
    public double getBalanceById(Long walletId) throws WalletNotFoundException {
        Wallet wallet = findById(walletId);
        return wallet.getBalance();
    }
}
