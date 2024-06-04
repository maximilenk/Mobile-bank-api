package com.ilenkov.bank.transactionservice.exceptions;

public class TransactionNotFoundException extends Exception {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
