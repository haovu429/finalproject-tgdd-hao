package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.entity.CustomerEntity;
import com.fsoft.finalproject.entity.OTP;
import com.fsoft.finalproject.entity.Token;
import com.fsoft.finalproject.entity.UserEntity;
import com.fsoft.finalproject.repository.CustomerRepository;
import com.fsoft.finalproject.repository.OTPRepository;
import com.fsoft.finalproject.repository.TokenRepository;
import com.fsoft.finalproject.repository.UserRepository;
import com.fsoft.finalproject.service.CustomerService;
import com.fsoft.finalproject.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OTPServiceImpl implements OTPService {
    @Autowired
    OTPRepository otpRepository;

    @Autowired
    CustomerRepository customerRepository;




    @Override
    public CustomerEntity findUSerByOTP(String otp){
        return otpRepository.findCustomerByOTP(otp);
    }

    @Override
    public void saveOTP(String otpValue, String userMail){
        CustomerEntity user = customerRepository.findOneByEmail(userMail);
        if(user == null){
            throw new RuntimeException("User was not found by email: " + userMail);
        }
        OTP otp = new OTP();
        otp.setUser(user);
        otp.setOtp(otpValue);
        otp.setCreateDate(new Date());

        otpRepository.save(otp);
    }


    @Override
    public void deleteToken(String otp){
        OTP entity = otpRepository.findByOtp(otp);
        if(entity == null)
            throw new RuntimeException("Token is not existed");
        otpRepository.delete(entity);
    }
}
