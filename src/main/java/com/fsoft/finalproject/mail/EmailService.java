package com.fsoft.finalproject.mail;

import com.fsoft.finalproject.entity.CustomerEntity;
import com.fsoft.finalproject.entity.UserEntity;
import org.springframework.scheduling.annotation.Async;

import javax.mail.internet.MimeMessage;

public interface EmailService {
  Mail createTokenMail(UserEntity user, String subject, String token);

  Mail createOTPMail(CustomerEntity customer, String subject, String otp);

  MimeMessage prepareMail(Mail mail, String pathTemplate);

  @Async("taskExecutor")
  void sendMail(Mail mail);

  void sendEmail(String toEmail, String subject, String body);

  void sendMail(Mail mail, String pathTemplate);
}
