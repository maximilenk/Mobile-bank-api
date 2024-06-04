package com.ilenkov.bank.identityservice.feighinterface;

import com.ilenkov.bank.identityservice.entity.dto.UserInfoRequest;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("user-info-service")
@Headers("Authorization: {token}")
public interface UserInfoInterface {
    @PostMapping("/userinfo")
    public ResponseEntity<?> createUser(@RequestHeader("Authorization") String token, @RequestBody UserInfoRequest userInfo);
}
