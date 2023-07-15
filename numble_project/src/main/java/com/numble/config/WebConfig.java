package com.numble.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.numble.interceptor.AuthenticationInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Autowired
	private AuthenticationInterceptor authenticationInterceptor;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/users") // 인증이 필요한 REST API 경로 패턴 설정
        		.addPathPatterns("/accounts");
    }
	
}
