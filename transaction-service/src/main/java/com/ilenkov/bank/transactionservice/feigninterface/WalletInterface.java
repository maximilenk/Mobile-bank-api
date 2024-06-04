package com.ilenkov.bank.transactionservice.feigninterface;


import com.ilenkov.bank.transactionservice.entity.dto.ChangeBalanceRequest;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("wallet-service")
@Headers("Authorization: {token}")
public interface WalletInterface {
    @GetMapping("/wallet/{walletId}")
    public ResponseEntity<?> getWalletById(@RequestHeader("Authorization") String token, @PathVariable Long walletId);
    @PutMapping("/wallet")
    public ResponseEntity<?> updateBalance(@RequestHeader("Authorization") String token, @RequestBody ChangeBalanceRequest balanceUpdate);
}
