package com.product_service_api.Authorization.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.product_service_api.Authorization.Interceptor.BearerTokenRequestInterceptor;

import feign.RequestInterceptor;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor bearerTokenRequestInterceptor() {
        return new BearerTokenRequestInterceptor();
    }
}
