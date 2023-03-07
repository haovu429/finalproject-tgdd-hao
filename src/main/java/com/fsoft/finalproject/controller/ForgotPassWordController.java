package com.fsoft.finalproject.controller;

import com.fsoft.finalproject.dto.ResetPassword;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class ForgotPassWordController {
    @Autowired
    SecurityService securityService;

    @PostMapping("forgot-password")
    public ResponseObject sendMailForgotPassword(@RequestParam("email") String email){
        return securityService.sendTokenForgotPwd(email);
    }

    @PostMapping("reset-password/{token}")
    public ResponseObject resetPassword(@PathVariable("token") String token,
                                        @RequestBody ResetPassword resetPassword){
        return securityService.resetPassword(token, resetPassword);
    }
}
