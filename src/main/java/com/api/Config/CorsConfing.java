package com.api.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfing {

    @Bean
    public CorsFilter corsFitter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 자바스크립트로 받을 수 있게 할건지
        config.addAllowedOrigin("*"); // 모든 IP 출처가 달라도 응답을 허용
        config.addAllowedHeader("*"); // 모든 헤데어 응답을 허용
        config.addAllowedMethod("*"); // 모든 메서드에 요청을 허용

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
