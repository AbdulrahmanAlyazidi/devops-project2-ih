package com.burgerbuilder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .cors(Customizer.withDefaults())  // يسمح بالـ CORS
      .csrf(csrf -> csrf.disable())     // يعطل CSRF
      .authorizeHttpRequests(auth -> auth
          .requestMatchers("/api/**").permitAll() // يسمح بالوصول للـ API بدون قيود
          .anyRequest().permitAll()               // يسمح لأي طلب ثاني مؤقتاً
      );

    return http.build();
  }
}
