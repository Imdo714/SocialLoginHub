package com.api.Config;

import com.api.OAuth.Filter.MyFilter1;
import com.api.OAuth.Service.CustomOAuth2FailureHandler;
import com.api.OAuth.Service.CustomOAuth2UserService;
import com.api.User.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;
    private final CorsFilter corsFilter;

    public SecurityConfig(UserRepository userRepository, CorsFilter corsFilter) {
        this.userRepository = userRepository;
        this.corsFilter = corsFilter;
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
//        http
//                .formLogin((auth) -> auth.disable()); // form login 비활성화

        http
                .logout(logout -> logout
                        .logoutUrl("/logout")
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
                .addFilter(corsFilter)
//                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 추가
                .addFilterBefore(new MyFilter1(), UsernamePasswordAuthenticationFilter.class); // SecurityConfig보다 먼저 실행

        return http.build();
    }

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
