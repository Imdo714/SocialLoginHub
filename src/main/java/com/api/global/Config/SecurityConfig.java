package com.api.global.Config;

import com.api.global.OAuth.CustomHandler.Handler.CustomLogoutSuccessHandler;
import com.api.global.OAuth.CustomHandler.Handler.CustomOAuth2FailureHandler;
import com.api.global.OAuth.CustomHandler.Handler.CustomOAuth2UserService;
import com.api.domain.User.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;
    private final CorsFilter corsFilter;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    public SecurityConfig(UserRepository userRepository, CorsFilter corsFilter, CustomLogoutSuccessHandler customLogoutSuccessHandler) {
        this.userRepository = userRepository;
        this.corsFilter = corsFilter;
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
    }

    @Bean
    public CustomOAuth2UserService customOAuth2UserService() {
        return new CustomOAuth2UserService(userRepository);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login/oauth2/code/*", "/oauth2/authorization/*").permitAll()  // OAuth2 리디렉션 URL 허용
                        .anyRequest().permitAll() // 모든 요청 허용
                );

        http
                .csrf((auth) -> auth.disable()); // CSRF 비활성화

        http
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(customLogoutSuccessHandler)
                        .logoutSuccessUrl("/") // 로그아웃 후 리디렉션
                        .invalidateHttpSession(true) // 세션 무효화
                        .deleteCookies("JSESSIONID", "RefreshToken") // JSESSIONID 쿠키 삭제
                );

        http
                .oauth2Login(oauth -> oauth
                .userInfoEndpoint(userInfo -> userInfo
                        .userService(customOAuth2UserService())
                )
                .defaultSuccessUrl("/loginSuccess", true) // 로그인 성공 시 리디렉션
                .failureHandler(new CustomOAuth2FailureHandler()) // 로그인 실패 시 리디렉션
        );

        http
                .addFilter(corsFilter); // CORS 직접 생성
//                .cors(cors -> cors.configurationSource(corsConfigurationSource())); // Spring Security에 CORS 설정

        return http.build();
    }


    // Spring Security에 CORS 설정
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        log.info("Cors 필터1 시작");
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true); // 자바스크립트로 받을 수 있게 할건지
//        config.addAllowedOrigin("*"); // 모든 IP 출처가 달라도 응답을 허용
//        config.addAllowedHeader("*"); // 모든 헤데어 응답을 허용
//        config.addAllowedMethod("*"); // 모든 메서드에 요청을 허용
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
}
