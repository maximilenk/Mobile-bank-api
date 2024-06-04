package com.ilenkov.bank.identityservice.exceptions;

public class AuthFailedException extends Exception {
    public AuthFailedException(String message) {
        super(message);
    }
}
