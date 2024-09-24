package com.shopping_service_api.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopping_service_api.Model.dto.UserDTO;

@FeignClient(name = "user-service-api")
public interface UserServiceClient {

    @GetMapping("/api/users/client/user/{id}")
    UserDTO getUserById(@PathVariable Long id);

    @GetMapping("/api/users/admin/verification")
    boolean isAdmin();

    @GetMapping("/api/users/user/verification")
    boolean IsUser();
}
