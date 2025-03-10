//package com.api.global.OAuth.Jwt.Filter;
//
//import com.api.global.OAuth.CustomHandler.Dto.CustomUser;
//import com.api.global.OAuth.Jwt.JwtTokenProvider;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collections;
//
//@Slf4j
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    // implements Filter: Spring Boot가 아닌 표준 Servlet 환경에서 작업하는 경우
//    // extends OncePerRequestFilter: Spring Boot 또는 Spring Security 기반의 프로젝트에서 요청당 한 번 실행되는 필터가 필요한 경우
//
//    private static final String SECRET_KEY = JwtTokenProvider.SECRET_KEY;
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    public JwtFilter(RedisTemplate<String, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String requestURI = request.getRequestURI();
//        // AntPathMatcher 으로 쉽게 동적 경로를 처리할 수 있다.
//        AntPathMatcher pathMatcher = new AntPathMatcher();
//
//        // 인증이 필요 없는 URL
//        if (pathMatcher.match("/", requestURI)||
//                pathMatcher.match("/loginSuccess", requestURI) ||
//                pathMatcher.match("/loginFailure", requestURI) ||
//                pathMatcher.match("favicon.ico", requestURI) ||
//                requestURI.startsWith("/public")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // 헤더에서 토큰 추출
//        String token = request.getHeader("Authorization");
//
//        if(token == null){
//            // token이 없음 잘못된 문법으로 인하여 서버가 요청을 히해할 수 없음
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().write("Access Token is missing");
//            return;
//        }
//
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7); // "Bearer " 부분 제거
//
//            try {
//                // 토큰 검증
//                Claims claims = Jwts.parser()
//                        .setSigningKey(SECRET_KEY)
//                        .parseClaimsJws(token)
//                        .getBody();
//
//                String getToken = (String) redisTemplate.opsForValue().get("accessToken:" + claims.getSubject());
//
//                if (!token.equals(getToken)) {
//                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                    return;
//                }
//
//                CustomUser customUser = new CustomUser(null, claims.getSubject(), null, null);
//                Authentication authentication =
//                        new UsernamePasswordAuthenticationToken(customUser, null, Collections.emptyList()); // 사용자 정보, 비밀번호, 권한 없음
//
//                // SecurityContext에 저장
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } catch (ExpiredJwtException e) { // Access Token 만료 시 발생
//                // 요청을 완수한 이후에 에이전트에게 문서 뷰를 리셋하라고 열려 줌 205
//                response.setStatus(HttpServletResponse.SC_RESET_CONTENT);
//                return;
//
//            }catch (Exception e) {
//                log.error("Exception e = {}", e.getMessage());
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.getWriter().write("Access Token UNAUTHORIZED");
//                return;
//            }
//        }
//
//        // 다음 필터 호출 또는 컨트롤러 실행
//        filterChain.doFilter(request, response);
//    }
//
//
//}
