package com.fsoft.finalproject.mail;

import net.bytebuddy.utility.RandomString;

public class OTPUtils {
    public static final int OTP_TIME = 1000*60*5;

    public static final int TOKEN_TIME = 1000*60*5;
    public static String generateOtp(){
        return RandomString.make(6);
    }

    public static String generateToken() {
        return RandomString.make(50);
    }
}
