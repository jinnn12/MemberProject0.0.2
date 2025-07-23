package com.cash.memberProject002.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity

public class SecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    //    @Bean은 메서드 위에 붙여 Return 되는 객체를 싱글톤 객체로 생성한다.
//    @Component는 클래스 위에 붙여 클래스자체를 싱글톤 객체로 생성한다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
//                cors : 특정도메인에 대한 허용정책, 사전에 약속 되어 있는 특정 도메인의 요청만 받겠다 / postman은 웹브라우저가 아니므로 cors 정책에 적용 X
                .cors(c -> c.configurationSource(corsConfiguration()))
//                csrf : (보안공격 중 하나로서 타 사이트의 쿠키값을 꺼내서 탈취 하는 공격)에 대해서 비활성화
//                세션기반 로그인(mvc 패턴, ssr)에서는 csrf 별도 설정하는 것이 일반적이나
//                토큰기반 로그인(rest api서버, csr)에서는 csrf 설정 않는 것이 일반적
                .csrf(AbstractHttpConfigurer::disable)
//                httpBasic : 인증 방법 중 하나, email/pw를 인코딩하여 인증(전송)하는 방식, 간단한 인증의 경우에만 사용한다 / email/pw를 인코딩하여 전송하는 것이 굉장히 위험함
                .httpBasic(AbstractHttpConfigurer::disable)
//                세션 로그인 방식 비활성화 (세션 : stateful인데(인증값을 서버에서 가지고 있음) -> STATELESS로 설정했으니 토큰로그인 방식으로 하겠다)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                 token을 검증하고, token 검증을 통해 Authentication 객체 생성
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    private CorsConfigurationSource corsConfiguration(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // 원하는 도메인 커스텀
        configuration.setAllowedMethods(Arrays.asList("*")); // 모든 HTTP(get, post, patch 등) 메서드 허용
        configuration.setAllowedHeaders(Arrays.asList("*")); // 모든 헤더요소(Authorization 등) 허용
        configuration.setAllowCredentials(true); // 자격 증명 허용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); //모든 url패턴에 대해 cors설정 적용, ** : 모든계층구조
        return source; // 모든 정책이 들어가 있음
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
