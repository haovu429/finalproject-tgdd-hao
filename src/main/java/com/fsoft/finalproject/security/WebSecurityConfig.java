package com.fsoft.finalproject.security;

import com.fsoft.finalproject.accessHandler.CustomAccessDeniedHandler;
import com.fsoft.finalproject.accessHandler.CustomAuthenticationHandler;
import com.fsoft.finalproject.filter.JwtAuthenticationFilter;
import com.fsoft.finalproject.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserService userService;

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter();
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    // Get AuthenticationManager bean
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth)
          throws Exception {
    auth.userDetailsService(userService)
            .passwordEncoder(passwordEncoder());
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new CustomAuthenticationHandler();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Enable CORS and disable CSRF
    http.cors().and().csrf().disable();

    // Set session management to stateless
    http = http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and();

    // Set unauthorized requests exception handler
    http = http
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint())
            .accessDeniedHandler(accessDeniedHandler())
            .and();

    // Set permissions on endpoints
    http.authorizeRequests()
//            customer
            .antMatchers(HttpMethod.GET, "/api/v1/addresses/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/address/**").permitAll()
            .antMatchers("/api/v1/customer-address").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/province").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/district").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/ward").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/promotion/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/product/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/bill/**").permitAll()
            .antMatchers("/api/v1/customer/**").permitAll()
            .antMatchers("/api/v1/productInCart/**").permitAll()
            .antMatchers("/api/v1/cart/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/promotion/**").permitAll()
            .antMatchers("/api/v1/order/cart/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/test-init").permitAll()

//            User and admin
//            .antMatchers(HttpMethod.GET, "/api/v1/user").hasAnyAuthority(Roles.ADMIN.value, Roles.USER.value)
            .antMatchers("/api/v1/address/**").hasAnyAuthority(Roles.ADMIN.value, Roles.USER.value)
            .antMatchers("/api/v1/province/**").hasAnyAuthority(Roles.ADMIN.value, Roles.USER.value)
            .antMatchers("/api/v1/district/**").hasAnyAuthority(Roles.ADMIN.value, Roles.USER.value)
            .antMatchers("/api/v1/ward/**").hasAnyAuthority(Roles.ADMIN.value, Roles.USER.value)
            .antMatchers("/api/v1/promotion/**").hasAnyAuthority(Roles.ADMIN.value, Roles.USER.value)
            .antMatchers("/api/v1/store/**").hasAnyAuthority(Roles.ADMIN.value, Roles.USER.value)
            .antMatchers("/api/v1/product/**").hasAnyAuthority(Roles.ADMIN.value, Roles.USER.value)
            .antMatchers("/api/v1/productsInStore/**").hasAnyAuthority(Roles.ADMIN.value, Roles.USER.value)


            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/api/v1/payment/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/v1/product/**").permitAll()
            .antMatchers("/api/v1/order/**").permitAll()
            .antMatchers("/api/v1/user/**").permitAll()//hasAuthority(Roles.ADMIN.value)
            .antMatchers("/api/v1/role/**").permitAll()//.hasAuthority(Roles.USER.value)
            .antMatchers("/api/v1/product/**").permitAll()//.hasAuthority(Roles.ADMIN.value)
            .antMatchers(HttpMethod.POST, "/api/v1/login").permitAll()
            .antMatchers(HttpMethod.POST, "/api/v1/phone/filter").permitAll()
            .antMatchers(HttpMethod.POST, "/api/v1/laptop/filter").permitAll()
            .antMatchers("/api/v1/forgot-password", "/api/v1/reset-password/**").permitAll()
            .antMatchers("/api/v1/customer-login").permitAll()
            .antMatchers("/api/v1/export-excel-sold-quantity","/pay","/pay/success","/pay/cancel").permitAll()
            .antMatchers("/api/v1/send-mail").hasAuthority(Roles.ADMIN.value)
            .antMatchers("/api/v1/customer/login").permitAll()
            .antMatchers("/api/v1/customer/validate-otp/**").permitAll()
            .anyRequest().authenticated();

//   filter check jwt token
    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

}
