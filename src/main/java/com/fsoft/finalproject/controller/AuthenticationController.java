package com.fsoft.finalproject.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.entity.UserEntity;
import com.fsoft.finalproject.repository.UserRepository;
import com.fsoft.finalproject.security.CustomUserDetails;
import com.fsoft.finalproject.security.JwtTokenProvider;
import com.fsoft.finalproject.sms.SendSMS;
import com.fsoft.finalproject.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1")
public class AuthenticationController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RedisUtils redisUtils;


  @PostMapping("/login")
  public ResponseObject authenticateUser(@Validated @RequestBody ObjectNode objectNode, @RequestParam(value = "rememberMe", required = false) Boolean rememberMe) {
    try {
      String a = objectNode.get("email").toString();
      UserEntity user = userRepository.findByEmail(objectNode.get("email").asText());
      if (user != null) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        objectNode.get("email").asText(),
                        objectNode.get("password").asText()
                )
        );
//        Set in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Long JWT_EXPIRATION = (long) (60 * 60 * 24 * 1000); //1 day
        if (rememberMe != null) {
          JWT_EXPIRATION *= 7; //7 days
        }
        // get jwt token
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal(), JWT_EXPIRATION);

        //store token in redis
        redisUtils.set(user.getEmail(), jwt);

        return new ResponseObject(200, "Login success", jwt);

      } else {
        return new ResponseObject("401", 200, "Login failed");
      }
    } catch (BadCredentialsException e) {
      throw new RuntimeException("Invalid username/password supplied");
    }
  }

  @GetMapping("/logout")
  public ResponseObject logout() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object user = authentication.getPrincipal();
    if (user instanceof CustomUserDetails) {
      //delete token in redis
      redisUtils.delete(((CustomUserDetails) user).getUsername());
      return new ResponseObject(200, "Logout success", "");
    }
    return new ResponseObject("401", 200, "Logout fail");
  }

}
