package com.ilenkov.bank.userinfoservice.service;

import com.ilenkov.bank.userinfoservice.entity.UserInfo;
import com.ilenkov.bank.userinfoservice.entity.dto.UserInfoRequest;
import com.ilenkov.bank.userinfoservice.exceptions.UserInfoAlreadyExists;
import com.ilenkov.bank.userinfoservice.exceptions.UserInfoNotFoundException;

public interface UserInfoService {
    UserInfo findById(Long userId) throws UserInfoNotFoundException;

    void create(UserInfoRequest userInfo) throws UserInfoAlreadyExists;

    void update(UserInfoRequest userInfo) throws UserInfoNotFoundException;
}
