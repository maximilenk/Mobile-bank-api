package com.ilenkov.bank.walletservice.exceptions;

public class WalletNotFoundException extends Exception{
    public WalletNotFoundException(String message){
        super(message);
    }
}
