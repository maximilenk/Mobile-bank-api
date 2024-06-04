package com.ilenkov.bank.transactionservice.controller;


import com.ilenkov.bank.transactionservice.entity.dto.TransferRequest;
import com.ilenkov.bank.transactionservice.exceptions.BadRequestException;
import com.ilenkov.bank.transactionservice.exceptions.NotEnoughSenderMoneyException;
import com.ilenkov.bank.transactionservice.exceptions.TransactionNotFoundException;
import com.ilenkov.bank.transactionservice.exceptions.WalletNotFoundException;
import com.ilenkov.bank.transactionservice.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransaction(@PathVariable Long transactionId) {
        try {
            return ResponseEntity.ok(transactionService.findById(transactionId));
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/wallettransactions/{walletId}")
    public ResponseEntity<?> getWalletTransactions(HttpServletRequest request, @PathVariable Long walletId) {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            return ResponseEntity.ok(transactionService.findAllByWalletId(walletId, authorizationHeader));
        } catch (WalletNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(HttpServletRequest request, @RequestBody TransferRequest transaction) {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            transactionService.addTransaction(transaction, authorizationHeader);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (WalletNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (NotEnoughSenderMoneyException | BadRequestException e ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
