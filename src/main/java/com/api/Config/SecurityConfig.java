package com.api.Config;

import com.api.OAuth.Filter.MyFilter1;
import com.api.OAuth.Service.CustomOAuth2UserService;
import com.api.User.Repository.UserRepository;
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
import org.springframework.web.filter.CorsFilter;


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
                        .deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제
                );

        http
                .oauth2Login(oauth -> oauth
                .userInfoEndpoint(userInfo -> userInfo
                        .userService(customOAuth2UserService())
                )
                .defaultSuccessUrl("/loginSuccess", true) // 로그인 성공 시 리디렉션
                .failureUrl("/loginFailure") // 로그인 실패 시 리디렉션
        );
        http
                .addFilter(corsFilter)
                .addFilterBefore(new MyFilter1(), UsernamePasswordAuthenticationFilter.class); // SecurityConfig보다 먼저 실행

        return http.build();
    }

}
