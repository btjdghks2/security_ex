package com.test.testSpring.testSecurity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // 요청시 보안 검사 시작
                .anyRequest().authenticated() // 어떤 요청에도 보안검사 시작
                .and()
                .formLogin() // 보안 검증은 formLogin 방식
                .loginPage("/login") // 사용자 정의 로그인 페이지
                .defaultSuccessUrl("/") // 로그인 성공후 이동 페이지
                .failureUrl("login?error=true")
                .usernameParameter("username") // 아이디 파라미터명
                .passwordParameter("password") // 패스워드 파라미터명
                .loginProcessingUrl("/login") // 로그인 Form Action Url
                .successHandler(loginSuccessHandler()) // 로그인 성공후 핸들러 (핸들러 생성해서 핸들링)
                .failureHandler(loginFailureHandler()) // 로그인 실패후 핸들러 (핸들러 생성해서 핸들링)
                .permitAll();

        .successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                                                HttpServletResponse response,
                                                FilterChain chain,
                                                Authentication authentication) throws IOException, ServletException {

                System.out.println("authentication:: "+ authentication.getName());
                response.sendRedirect("/");

            }
        })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        AuthenticationException exception) throws IOException, ServletException {
                        System.out.println("exception:: "+exception.getMessage());
                        response.sendRedirect("/login");
                    }
                });
        return null;
    }




}
