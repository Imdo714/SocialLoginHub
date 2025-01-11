package com.api.OAuth.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class MyFilter1 extends OncePerRequestFilter {

    // implements Filter: Spring Boot가 아닌 표준 Servlet 환경에서 작업하는 경우
    // extends OncePerRequestFilter: Spring Boot 또는 Spring Security 기반의 프로젝트에서 요청당 한 번 실행되는 필터가 필요한 경우

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        if (pathMatcher.match("/", requestURI)){
            filterChain.doFilter(request, response);
            return;
        }
        log.info("필터1 시작");

        log.info("필터1 끝");
    }


}
