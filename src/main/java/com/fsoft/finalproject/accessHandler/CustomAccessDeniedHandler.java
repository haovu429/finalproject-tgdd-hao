package com.fsoft.finalproject.accessHandler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.exception.CustomException;
import org.mapstruct.Qualifier;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  public static final Logger LOG = org.slf4j.LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
  // Jackson JSON serializer instance
  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    Authentication auth
            = SecurityContextHolder.getContext().getAuthentication();
    ResponseObject responseObject = new ResponseObject("403", 200, accessDeniedException.getMessage());

    if (auth != null) {
      LOG.warn("User: " + auth.getName()
              + " access denied the protected URL: "
              + request.getRequestURI());
      responseObject.setMessage("User: " + auth.getName()
              + " access denied the protected URL: "
              + request.getRequestURI());
    }

    response.setStatus(200);
    response.setContentType("application/json");
    response.getWriter().write(objectMapper.writeValueAsString(responseObject));

  }
}
