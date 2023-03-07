package com.fsoft.finalproject.repository;

import com.fsoft.finalproject.entity.CustomerEntity;
import com.fsoft.finalproject.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OTPRepository extends JpaRepository<OTP, Integer> {

    @Query("from OTP as o  where o.user.email = :email ")
    List<OTP> findOTPsByCustomerMail(@Param("email") String email);

    @Query("select o.user from OTP as o where o.otp = :otp")
    CustomerEntity findCustomerByOTP(@Param("otp") String otp);

    OTP findByOtp(String otp);
}
