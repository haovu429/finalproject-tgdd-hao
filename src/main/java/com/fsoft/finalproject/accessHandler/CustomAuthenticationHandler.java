package com.fsoft.finalproject.accessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsoft.finalproject.dto.ResponseObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationHandler implements AuthenticationEntryPoint {

  // Jackson JSON serializer instance
  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    ResponseObject responseObject = new ResponseObject("401", 200, authException.getMessage());
    responseObject.setMessage("Invalid email or password");
    response.setStatus(200);
    response.setContentType("application/json");
    response.getWriter().write(objectMapper.writeValueAsString(responseObject));

  }
}
