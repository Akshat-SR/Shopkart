package com.ak.shopkart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ak.shopkart.jwt.JwtFilter;

@Configuration
public class SecurityConfig {

  @Autowired
  private JwtFilter jwtFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth

            // Public endpoints
            .requestMatchers(
                "/api/users/register",
                "/api/users/login",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html")
            .permitAll()

            // ADMIN endpoints
            .requestMatchers("/api/products/**").hasRole("ADMIN")
            .requestMatchers("/api/categories/**").hasRole("ADMIN")
            .requestMatchers("/api/admin/**").hasRole("ADMIN")

            // USER endpoints
            .requestMatchers("/api/cart/**").hasRole("USER")
            .requestMatchers("/api/orders/**").hasRole("USER")
            .requestMatchers("/api/address/**").hasRole("USER")

            // everything else
            .anyRequest().authenticated())
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}