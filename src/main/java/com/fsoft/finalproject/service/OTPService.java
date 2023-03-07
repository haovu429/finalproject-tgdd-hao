package com.fsoft.finalproject.service;

import com.fsoft.finalproject.entity.CustomerEntity;

public interface OTPService {
    CustomerEntity findUSerByOTP(String otp);

    void saveOTP(String otpValue, String userMail);

    void deleteToken(String otp);
}
