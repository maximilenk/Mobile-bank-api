package com.ilenkov.bank.userinfoservice.controller;


import com.ilenkov.bank.userinfoservice.entity.UserInfo;
import com.ilenkov.bank.userinfoservice.entity.dto.UserInfoRequest;
import com.ilenkov.bank.userinfoservice.exceptions.UserInfoAlreadyExists;
import com.ilenkov.bank.userinfoservice.exceptions.UserInfoNotFoundException;
import com.ilenkov.bank.userinfoservice.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(userInfoService.findById(userId));
        } catch (UserInfoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserInfoRequest userInfo) {
        try {
            userInfoService.create(userInfo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserInfoAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserInfoRequest userInfo) {
        try {
            userInfoService.update(userInfo);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (UserInfoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
