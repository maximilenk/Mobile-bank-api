package com.ilenkov.bank.walletservice.feigninterface;


import feign.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("user-info-service")
@Headers("Authorization: {token}")
public interface UserInterface {
    @GetMapping("/userinfo/{userId}")
    public ResponseEntity<?> getUserById(@RequestHeader("Authorization") String token, @PathVariable Long userId);
}
