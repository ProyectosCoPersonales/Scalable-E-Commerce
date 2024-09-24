package com.order_service_api.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order_service_api.Authorization.Config.FeignConfig;
import com.order_service_api.Model.dto.CartDTO;

@FeignClient(name="shopping-service-api", configuration = FeignConfig.class)
public interface ShopServiceClient {

    @GetMapping("/api/shopping/send-cart/{idUser}")
    CartDTO sendCart(@PathVariable Long idUser);

    @GetMapping("/api/shopping/clear-cart/{idUser}")
    void cleanCart(@PathVariable Long idUser);
}
