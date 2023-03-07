package com.fsoft.finalproject.service;

import com.fsoft.finalproject.dto.ResetPassword;
import com.fsoft.finalproject.dto.ResponseObject;

public interface SecurityService {

    ResponseObject sendTokenForgotPwd(String email);

    ResponseObject resetPassword(String token, ResetPassword resetPassword);
}
