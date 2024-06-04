package com.ilenkov.bank.identityservice.controller;


import com.ilenkov.bank.identityservice.entity.UserCredential;
import com.ilenkov.bank.identityservice.entity.dto.AuthRequest;
import com.ilenkov.bank.identityservice.exceptions.AuthFailedException;
import com.ilenkov.bank.identityservice.exceptions.UserAlreadyExistsException;
import com.ilenkov.bank.identityservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody UserCredential userCredential) {
        try {
            authService.saveUser(userCredential);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody AuthRequest authRequest) {
        try {
            return ResponseEntity.ok(authService.generateToken(authRequest));
        } catch (AuthFailedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/validate")
    public void validateToken(@RequestParam String token) {
        authService.validateToken(token);
    }
}
