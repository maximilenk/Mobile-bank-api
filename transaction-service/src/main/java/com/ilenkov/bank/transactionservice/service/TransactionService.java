package com.ilenkov.bank.transactionservice.service;

import com.ilenkov.bank.transactionservice.entity.Transaction;
import com.ilenkov.bank.transactionservice.entity.dto.TransferRequest;
import com.ilenkov.bank.transactionservice.exceptions.*;

import java.util.List;

public interface TransactionService {
    void addTransaction(TransferRequest transaction, String authorizationHeader) throws WalletNotFoundException, NotEnoughSenderMoneyException, BadRequestException;
    Transaction findById(Long id) throws TransactionNotFoundException;
    List<Transaction> findAllByWalletId(Long walletId, String authorizationHeader) throws WalletNotFoundException;

}
