package com.ilenkov.bank.transactionservice.exceptions;

public class NotEnoughSenderMoneyException extends Exception {
    public NotEnoughSenderMoneyException(String message) {
        super(message);
    }
}
