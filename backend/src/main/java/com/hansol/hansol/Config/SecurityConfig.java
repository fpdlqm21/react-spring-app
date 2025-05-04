package com.hansol.hansol.Config;

import com.hansol.hansol.Service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 모든 url요청 security 제어 받음
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailService userService;

    /* 로그인 인증 관리자 설정
    * AuthenticationManager -> 로그인 인증 관리자
    * DaoAuthenticationProvider -> DB에서 실제 사용자 정보 확인하고 암호 비교하는 녀석
    * UserDetailsService -> AuthenticationManager에게 사용자 정보 어디서 확인할지 알려줌
    * BCryptPasswordEncoder -> password 해독기
    * */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService)
    throws Exception{
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return new ProviderManager(authProvider);
    }

    //    PasswordEncoder Bean 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    FilterChain 설정
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
