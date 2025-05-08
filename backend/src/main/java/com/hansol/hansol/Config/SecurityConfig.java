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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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
                .csrf(csrf -> csrf.disable()) // csrf 공격 막기위한 기능 (개발단계에선 통신 문제 때문에 보통 비활성화)
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 다른 출처(port)에서 오는 요청 허용
                //허용할 URL 요청 설정(authorizeHttpRequests)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login**",
                                "/form",
                                "/user",
                                "/images/**",
                                "/api/**"
                        ).permitAll() // 로그인 없이도 접근 가능한 페이지 설정
                        .anyRequest().authenticated() // 그외 요청은 인가된 사용자만 접근 가능
                )
//                폼 기반 user 로그인 설정
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/weather", true)
                )
//                간편 로그인 설정(구글, 카카오)
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // 로그인 페이지 커스터마이징
                        .defaultSuccessUrl("/weather", true)); // 로그인 성공하면 무조건 /(메인 페이지)경로로 이동

        return http.build(); // 위에서 설정한 보안 필터 체인 반환

    }

//    CORS 설정 메소드
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:3000")); // React 개발 서버 주소 허용
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // 허용할 http 메소드 지정
        config.setAllowedHeaders(List.of("*")); // 모든 헤더 허용
        config.setAllowCredentials(true); // 쿠키나 세션 같은 인증 정보를 포함할 수 있도록 허용

//        /api/**뿐만 아니라, 모든 경로/에 적용함
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
