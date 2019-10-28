package com.oktenweb.medbookback.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LocalDateInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("--------------preHandle--------------");
//        System.out.println(request.getServletContext().);
//        System.out.println(request.getLocalName());
        System.out.println("--------------preHandle---end--------");
        return true;
    }


}
