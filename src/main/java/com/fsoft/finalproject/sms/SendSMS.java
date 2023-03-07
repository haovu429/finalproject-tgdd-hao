package com.fsoft.finalproject.sms;

import com.fsoft.finalproject.utils.Constant;

import java.io.IOException;

public class SendSMS {

    public static String send() throws IOException {
        SpeedSMSAPI speedSMSAPI = new SpeedSMSAPI(Constant.SMS_ACCESS_TOKEN);
        String content = "Ma cua ban la 12345" ;
        String res = speedSMSAPI.sendSMS("0376690904", content, 1, "");
        return res;
    }

    public static String get() throws IOException {
        SpeedSMSAPI api = new SpeedSMSAPI(Constant.SMS_ACCESS_TOKEN);
        return api.getUserInfo();
    }

}
