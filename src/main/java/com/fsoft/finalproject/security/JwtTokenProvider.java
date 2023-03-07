package com.fsoft.finalproject.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
  private final String JWT_SECRET = "fptsoftware";

  public String generateToken(CustomUserDetails userDetails, long JWT_EXPIRATION) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
    // create JWT token
    return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(now)
            .claim("roles", userDetails.getAuthorities())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, JWT_SECRET.getBytes())
            .compact();
  }

  public String getEmailFromJWT(String token) {
    Claims claims = Jwts.parser()
            .setSigningKey(JWT_SECRET.getBytes())
            .parseClaimsJws(token)
            .getBody();

    return String.valueOf(claims.getSubject());
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(JWT_SECRET.getBytes()).parseClaimsJws(authToken).getBody();
      return true;
    } catch (MalformedJwtException ex) {
      System.out.println("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      System.out.println("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      System.out.println("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      System.out.println("JWT claims string is empty.");
    }
    return false;
  }

}
