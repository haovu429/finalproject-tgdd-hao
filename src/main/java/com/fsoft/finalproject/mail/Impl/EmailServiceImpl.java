package com.fsoft.finalproject.mail.Impl;

import com.fsoft.finalproject.entity.CustomerEntity;
import com.fsoft.finalproject.entity.UserEntity;
import com.fsoft.finalproject.mail.EmailService;
import com.fsoft.finalproject.mail.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {
  @Autowired JavaMailSender javaMailSender;
  @Autowired SpringTemplateEngine templateEngine;

  @Override
  public Mail createTokenMail(UserEntity user, String subject, String token) {
    Mail mail = new Mail();
    mail.setFrom("fpt.training.hcmute@gmail.com");
    mail.setTo(user.getEmail());
    mail.setSubject(subject);

    Map<String, Object> model = new HashMap<>();
    model.put("username", user.getName());
    model.put("signature", "From FPT_TRAINING_HCMUTE");
    model.put("WEBSITE_NAME", "DEMO_TGDD");
    String url = "http://localhost:8080/api/v1/reset-password/" + token;
    model.put("resetUrl", url);

    mail.setModel(model);

    return mail;
  }

  @Override
  public Mail createOTPMail(CustomerEntity customer, String subject, String otp) {
    Mail mail = new Mail();
    mail.setFrom("fpt.training.hcmute@gmail.com");
    mail.setTo(customer.getEmail());
    mail.setSubject(subject);

    Map<String, Object> model = new HashMap<>();
    model.put("username", customer.getFullName());
    model.put("signature", "From FPT_TRAINING_HCMUTE");
    model.put("WEBSITE_NAME", "DEMO_TGDD");
    model.put("otp", otp);
    mail.setModel(model);
    return mail;
  }

  @Override
  public MimeMessage prepareMail(Mail mail, String pathTemplate) {
    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper =
          new MimeMessageHelper(
              message,
              MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
              StandardCharsets.UTF_8.name());
      Context context = new Context();
      context.setVariables(mail.getModel());
      String html = templateEngine.process(pathTemplate, context);

      helper.setTo(mail.getTo());
      helper.setFrom(mail.getFrom());
      helper.setSubject(mail.getSubject());
      helper.setText(html, true);
      return message;
    } catch (MessagingException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public void sendMail(Mail mail) {}

  @Override
  @Async("taskExecutor")
  public void sendMail(Mail mail, String pathTemplate) {
    MimeMessage message = prepareMail(mail, pathTemplate);
    javaMailSender.send(message);
  }

  @Override
  public void sendEmail(String toEmail, String subject, String body) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom("fpt.training.hcmute@gmail.com");
    mailMessage.setTo(toEmail);
    mailMessage.setText(body);
    mailMessage.setSubject(subject);

    javaMailSender.send(mailMessage);
  }
}
