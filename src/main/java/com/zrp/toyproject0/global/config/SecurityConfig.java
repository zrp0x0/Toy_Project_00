package com.zrp.toyproject0.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.csrf((csrf) -> csrf.disable());
      http.authorizeHttpRequests((authorize) ->
          authorize
          .requestMatchers("/admin/**").hasAuthority("admin")
          .requestMatchers(
            "/",
            "/item/list",
            "/item/detail/**",
            "/register",
            "/login"
          ).permitAll()
          .anyRequest()
          .authenticated()
      );

      // 로그인
      http.formLogin((form) -> form
        .loginPage("/login")             // 내가 만든 커스텀 로그인 페이지
        .defaultSuccessUrl("/item/list") // 로그인 성공 시 이동할 곳
        .permitAll()
      );

      // 로그아웃
      http.logout((logout) -> logout
        .logoutSuccessUrl("/item/list")
        .invalidateHttpSession(true)     // 세션 무효화
        .deleteCookies("JSESSIONID")     // 쿠키 삭제
      );

      return http.build();
    }
  
}
