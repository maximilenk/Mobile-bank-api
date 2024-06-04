package com.ilenkov.bank.userinfoservice.service;


import com.ilenkov.bank.userinfoservice.entity.UserInfo;
import com.ilenkov.bank.userinfoservice.entity.dto.UserInfoRequest;
import com.ilenkov.bank.userinfoservice.exceptions.UserInfoAlreadyExists;
import com.ilenkov.bank.userinfoservice.exceptions.UserInfoNotFoundException;
import com.ilenkov.bank.userinfoservice.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserInfo findById(Long userId) throws UserInfoNotFoundException {
        return userInfoRepository.findByUserId(userId)
                .orElseThrow(()-> new UserInfoNotFoundException("User with id " + userId + " not found"));
    }

    @Override
    public void create(UserInfoRequest userInfo) throws UserInfoAlreadyExists {
        boolean exists = userInfoRepository.findByUserId(userInfo.getUser_id()).isPresent();
        if (!exists) {
            UserInfo newUserInfo = new UserInfo();
            newUserInfo.setUserId(userInfo.getUser_id());
            newUserInfo.setEmail(userInfo.getEmail());
            newUserInfo.setName(userInfo.getName());
            userInfoRepository.save(newUserInfo);
        } else {
            throw new UserInfoAlreadyExists("Userinfo for user with id " + userInfo.getUser_id() + " already exists");
        }
    }

    @Override
    public void update(UserInfoRequest userInfo) throws UserInfoNotFoundException {
        UserInfo exsitedUserInfo = findById(userInfo.getUser_id());
        exsitedUserInfo.setName(userInfo.getName());
        exsitedUserInfo.setEmail(userInfo.getEmail());
        userInfoRepository.save(exsitedUserInfo);
    }
}
