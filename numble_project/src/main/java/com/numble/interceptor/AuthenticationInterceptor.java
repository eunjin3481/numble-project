package com.numble.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor{
    private static final String AUTH_HEADER = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader(AUTH_HEADER);

        // 토큰 유효성 검사 및 인증 처리
        // ...
        /*
        if (authenticated) {
            return true; // 인증 성공 시 요청 계속 진행
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 인증 실패 시 401 Unauthorized 응답
            return false; // 인증 실패 시 요청 중단
        }
        */
        return true;
    }

}
