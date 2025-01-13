package com.api.Config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    /**
     *  SecurityConfig가 끝나고 필더 실행이 됨 먼저 실행하고 싶으면
     *  SecurityConfig에 .addFilterBefore(new MyFilter1(), UsernamePasswordAuthenticationFilter.class); 추가해서
     *  UsernamePasswordAuthenticationFilter후에 필터 실행 하면 SecurityConfig보다 먼저 실행이 가능하다.
     */

//    @Bean
//    public FilterRegistrationBean<MyFilter1> filter1(){
//        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
//        bean.addUrlPatterns("/*"); // 패턴이 들어오면
//        bean.setOrder(0);   // 낮은 번호일수록 먼저 실행
//        return bean;
//    }
}
