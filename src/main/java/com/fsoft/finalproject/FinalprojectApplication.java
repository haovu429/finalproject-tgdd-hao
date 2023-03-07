package com.fsoft.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableSwagger2
@EnableAsync
@EnableScheduling
// http://localhost:8080/swagger-ui/index.html
public class FinalprojectApplication {

  public static void main(String[] args) {

    SpringApplication.run(FinalprojectApplication.class, args);
  }
}
