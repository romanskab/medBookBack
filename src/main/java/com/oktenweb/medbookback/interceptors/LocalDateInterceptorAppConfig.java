package com.oktenweb.medbookback.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class LocalDateInterceptorAppConfig implements WebMvcConfigurer {

    @Autowired
    LocalDateInterceptor localDateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localDateInterceptor);
    }
}
