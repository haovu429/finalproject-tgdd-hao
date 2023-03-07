package com.fsoft.finalproject.filter;

import com.fsoft.finalproject.security.JwtTokenProvider;
import com.fsoft.finalproject.security.UserService;
import com.fsoft.finalproject.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private UserService customUserDetailsService;

  @Autowired
  private RedisUtils redisUtils;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
    try {
      // get request token from header
      String jwt = getJwtFromRequest(request);

      if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
        // get email from token
        String email = tokenProvider.getEmailFromJWT(jwt);
        // get user from email
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        // get token from redis
        String token = redisUtils.get(email) == null ? "" : redisUtils.get(email).toString();
        if (userDetails != null && token.equals(jwt)) {
          // set authentication to context
          UsernamePasswordAuthenticationToken
                  authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    } catch (AccessDeniedException ex) {
      throw new AccessDeniedException("Access Denied");
    } catch (Exception exception) {
      log.error("failed on set user authentication", exception);
    }

    filterChain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    // Check if bearer token is valid
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}