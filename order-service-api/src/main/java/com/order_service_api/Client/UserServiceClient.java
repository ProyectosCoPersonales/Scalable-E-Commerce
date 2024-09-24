package com.order_service_api.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order_service_api.Authorization.Config.FeignConfig;
import com.order_service_api.Model.dto.UserDTO;

@FeignClient(name = "user-service-api", configuration = FeignConfig.class)
public interface UserServiceClient {

    @GetMapping("/api/users/admin/verification")
    boolean isAdmin();

    @GetMapping("/api/users/client/verification")
    boolean isClient();

    @GetMapping("/api/users/client/user/{id}")
    UserDTO getUserById(@PathVariable Long id);

}
