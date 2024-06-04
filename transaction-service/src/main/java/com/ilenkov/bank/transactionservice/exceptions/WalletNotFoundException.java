package com.ilenkov.bank.transactionservice.exceptions;

public class WalletNotFoundException extends Exception {
    public WalletNotFoundException(String message) {
        super(message);
    }
}
