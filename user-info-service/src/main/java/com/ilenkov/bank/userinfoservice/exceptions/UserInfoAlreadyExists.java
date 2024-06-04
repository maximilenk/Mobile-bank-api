package com.ilenkov.bank.userinfoservice.exceptions;

public class UserInfoAlreadyExists extends Exception {
    public UserInfoAlreadyExists(String message) {
        super(message);
    }
}
