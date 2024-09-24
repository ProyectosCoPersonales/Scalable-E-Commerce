package com.product_service_api.Authorization.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.product_service_api.Authorization.Config.FeignConfig;

@FeignClient(name = "user-service-api", configuration = FeignConfig.class)
public interface AuthServiceClient {
    @GetMapping("/api/users/admin/verification")
    boolean isAdmin();

    @GetMapping("/api/users/user/verification")
    boolean IsUser();
}
