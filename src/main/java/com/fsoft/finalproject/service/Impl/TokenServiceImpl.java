package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.entity.Token;
import com.fsoft.finalproject.entity.UserEntity;
import com.fsoft.finalproject.repository.TokenRepository;
import com.fsoft.finalproject.repository.UserRepository;
import com.fsoft.finalproject.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity findUSerByToken(String token){
        return tokenRepository.findUserByToken(token);
    }

    @Override
    public void saveToken(String tokenValue, String userMail){
        UserEntity user = userRepository.findByEmail(userMail);
        if(user == null){
            throw new RuntimeException("User was not found by email: " + userMail);
        }
        Token token = new Token();
        token.setUser(user);
        token.setToken(tokenValue);
        token.setCreateDate(new Date());

        tokenRepository.save(token);
    }

    @Override
    public void deleteToken(String tokenValue){
        Token token = tokenRepository.findByToken(tokenValue);
        if(token == null)
            throw new RuntimeException("Token is not existed");
        tokenRepository.delete(token);
    }
}
