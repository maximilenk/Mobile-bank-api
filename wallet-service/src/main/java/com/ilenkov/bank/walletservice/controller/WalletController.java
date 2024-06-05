package com.ilenkov.bank.walletservice.controller;


import com.ilenkov.bank.walletservice.entity.dto.CreateWalletRequest;
import com.ilenkov.bank.walletservice.entity.dto.WalletUpdateBalanceRequest;
import com.ilenkov.bank.walletservice.exceptions.NotEnoughMoneyException;
import com.ilenkov.bank.walletservice.exceptions.OwnerNotFoundException;
import com.ilenkov.bank.walletservice.exceptions.WalletNotFoundException;
import com.ilenkov.bank.walletservice.service.WalletService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }
    @GetMapping("/userwallets/{ownerId}")
    public ResponseEntity<?> getWalletsByOwnerId(HttpServletRequest request, @PathVariable Long ownerId) {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            return ResponseEntity.ok(walletService.findAllByOwnerId(ownerId, authorizationHeader));
        } catch (OwnerNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<?> getWalletById(@PathVariable Long walletId) {
        try {
            return ResponseEntity.ok(walletService.findById(walletId));
        } catch (WalletNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createWallet(HttpServletRequest request, @RequestBody CreateWalletRequest createWalletRequest) {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            walletService.create(createWalletRequest.getOwner_id(), authorizationHeader);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateBalance(@RequestBody WalletUpdateBalanceRequest balanceUpdate) {
        try {
            walletService.updateBalance(balanceUpdate);
            return ResponseEntity.ok().build();
        } catch (WalletNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (NotEnoughMoneyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/balance/{walletId}")
    public ResponseEntity<?> getBalance(@PathVariable Long walletId) {
        try {
            return ResponseEntity.ok(walletService.getBalanceById(walletId));
        } catch (WalletNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
