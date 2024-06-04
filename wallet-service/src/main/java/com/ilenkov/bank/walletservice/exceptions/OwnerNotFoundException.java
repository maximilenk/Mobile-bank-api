package com.ilenkov.bank.walletservice.exceptions;

public class OwnerNotFoundException extends Exception{
    public OwnerNotFoundException(String message){
        super(message);
    }
}
