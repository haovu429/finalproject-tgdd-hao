package com.fsoft.finalproject.statistic;

import com.fsoft.finalproject.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;

@Component
public class AutoSendMail {
    @Autowired
    EmailService emailSenderService;
    @Scheduled(cron = "0 0 22 * * *")
    public void sendMail() {
        Date date=java.util.Calendar.getInstance().getTime();
        int day = java.time.LocalDate.now().getDayOfMonth();
        int month = java.time.LocalDate.now().getMonthValue();
        int year = java.time.LocalDate.now().getYear();
        String url = "http://localhost:8080/api/v1/export-excel-sold-quantity?day="+day+"&month="+month+"&year="+year;
        emailSenderService.sendEmail("daonthanh14092001@gmail.com","Mail Thong Ke",url);
        System.out.println("Scheduled task running"+day+month+year);

    }
}
