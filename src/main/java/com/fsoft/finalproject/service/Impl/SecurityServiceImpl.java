package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.dto.ResetPassword;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.Token;
import com.fsoft.finalproject.entity.UserEntity;
import com.fsoft.finalproject.mail.EmailService;
import com.fsoft.finalproject.mail.Mail;
import com.fsoft.finalproject.mail.OTPUtils;
import com.fsoft.finalproject.repository.TokenRepository;
import com.fsoft.finalproject.repository.UserRepository;
import com.fsoft.finalproject.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseObject sendTokenForgotPwd(String email){
        UserEntity foundUser = userRepository.getUserByEmail(email);
        if(foundUser == null){
            throw new RuntimeException("Can not found User Account with email " + email);
        }

        List<Token> existedTokens = tokenRepository.findTokensByUser(foundUser.getId());

        if(!existedTokens.isEmpty()){
            List<Token> validTokens = existedTokens.stream()
                    .filter(token -> new Date(token.getCreateDate().getTime() + OTPUtils.TOKEN_TIME).before(new Date()))
                    .collect(Collectors.toList());
            if(validTokens.size() == 0 ||validTokens == null)
                throw new RuntimeException("You just requested change password, try again after 5 minutes");
        }
        String tokenValue = OTPUtils.generateToken();
        Token createToken = new Token(tokenValue, new Date(), foundUser);
        tokenRepository.save(createToken);

        Mail mail = emailService.createTokenMail(foundUser, "RESOLVE REQUEST FOR CHANGE PASSWORD", tokenValue);
        emailService.sendMail(mail, "mail/token-reset-pwd");

        return new ResponseObject( 200, "send email successful", null );
    }

    @Override
    public ResponseObject resetPassword(String token, ResetPassword resetPassword){
        Token foundToken = tokenRepository.findByToken(token);
        if(foundToken == null){
            throw new RuntimeException("Token is invalid");
        }
        Date expired = new Date(foundToken.getCreateDate().getTime() + OTPUtils.TOKEN_TIME);
        if(expired.before(new Date())){
            throw new RuntimeException("Token was expired");
        }
        UserEntity user = tokenRepository.findUserByToken(token);
        if(user == null)
            throw new RuntimeException("No user was found");
        if(!resetPassword.getOldPass().equals(resetPassword.getNewPass())){
            throw new RuntimeException("Confirm password fail");
        }
        user.setPassword(passwordEncoder.encode(resetPassword.getNewPass()));
        userRepository.save(user);
        tokenRepository.delete(foundToken);

        return new ResponseObject(200, "change password successful", null);
    }
}
