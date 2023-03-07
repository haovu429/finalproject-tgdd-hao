package com.fsoft.finalproject.service;

import com.fsoft.finalproject.entity.UserEntity;

public interface TokenService {
    UserEntity findUSerByToken(String token);

    void saveToken(String tokenValue, String userMail);

    void deleteToken(String tokenValue);
}
