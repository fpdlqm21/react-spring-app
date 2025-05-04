package com.hansol.hansol.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                //허용할 URL 요청 설정(authorizeHttpRequests)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login**", "/form", "/images/**").permitAll() // 로그인 없이도 접근 가능한 페이지 설정
                        .anyRequest().authenticated() // 그외 요청은 인가된 사용자만 접근 가능
                )
//                간편 로그인 설정(구글, 카카오)
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // 로그인 페이지 커스터마이징
                        .defaultSuccessUrl("/weather", true)); // 로그인 성공하면 무조건 /(메인 페이지)경로로 이동

        return http.build(); // 위에서 설정한 보안 필터 체인 반환
    }
}
