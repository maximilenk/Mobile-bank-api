package com.ilenkov.bank.identityservice.service;

import com.ilenkov.bank.identityservice.entity.UserCredential;
import com.ilenkov.bank.identityservice.entity.dto.AuthRequest;
import com.ilenkov.bank.identityservice.exceptions.AuthFailedException;
import com.ilenkov.bank.identityservice.exceptions.UserAlreadyExistsException;

public interface AuthService {
    void saveUser(UserCredential userCredential) throws UserAlreadyExistsException;
    String generateToken(AuthRequest authRequest) throws AuthFailedException;
    void validateToken(String token);

}
