package com.ilenkov.bank.transactionservice.service;

import com.ilenkov.bank.transactionservice.entity.Transaction;
import com.ilenkov.bank.transactionservice.entity.dto.ChangeBalanceRequest;
import com.ilenkov.bank.transactionservice.entity.dto.TransferRequest;
import com.ilenkov.bank.transactionservice.exceptions.BadRequestException;
import com.ilenkov.bank.transactionservice.exceptions.NotEnoughSenderMoneyException;
import com.ilenkov.bank.transactionservice.exceptions.TransactionNotFoundException;
import com.ilenkov.bank.transactionservice.exceptions.WalletNotFoundException;
import com.ilenkov.bank.transactionservice.feigninterface.WalletInterface;
import com.ilenkov.bank.transactionservice.repository.TransactionRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletInterface walletInterface;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  WalletInterface walletInterface) {
        this.transactionRepository = transactionRepository;
        this.walletInterface = walletInterface;
    }

    @Override
    public Transaction findById(Long id) throws TransactionNotFoundException {
        return transactionRepository.findById(id)
                .orElseThrow(()->new TransactionNotFoundException("transaction with id " + id + " not found"));
    }

    @Override
    public List<Transaction> findAllByWalletId(Long walletId, String authorizationHeader) throws WalletNotFoundException {
        try {
            ResponseEntity<?> response = walletInterface.getWalletById(authorizationHeader, walletId);
            List<Transaction> transactions = transactionRepository.findByReceiverWalletId(walletId);
            transactions.addAll(transactionRepository.findBySenderWalletId(walletId));
            return transactions;
        } catch (FeignException e) {
            throw new WalletNotFoundException("wallet with id " + walletId + " not found");
        }
    }

    @Override
    public void addTransaction(TransferRequest transaction, String authorizationHeader) throws WalletNotFoundException, NotEnoughSenderMoneyException, BadRequestException {
        if (transaction.getAmount() <= 0) {
            throw new BadRequestException("amount must be greater than zero");
        }
        try {
            ResponseEntity<?> senderResponse = walletInterface.getWalletById(authorizationHeader,
                    transaction.getSenderWalletId());
            ResponseEntity<?> receiverResponse = walletInterface.getWalletById(authorizationHeader,
                    transaction.getReceiverWalletId());
            try {
                walletInterface.updateBalance(authorizationHeader,
                        new ChangeBalanceRequest(transaction.getSenderWalletId(),
                                -1 * transaction.getAmount()));
                walletInterface.updateBalance(authorizationHeader,
                        new ChangeBalanceRequest(transaction.getReceiverWalletId(),
                                transaction.getAmount()));
                transactionRepository.save(new Transaction(transaction.getSenderWalletId(),
                        transaction.getReceiverWalletId(), transaction.getAmount()));
            } catch (FeignException e) {
                throw new NotEnoughSenderMoneyException("Not enough sender money");
            }

        } catch (FeignException e) {
            throw new WalletNotFoundException("Sender or receiver wallet not found");
        }
    }
}
