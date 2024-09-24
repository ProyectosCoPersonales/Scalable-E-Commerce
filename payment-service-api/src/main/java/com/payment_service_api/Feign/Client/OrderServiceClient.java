package com.payment_service_api.Feign.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.payment_service_api.Model.dto.OrderDTO;

@FeignClient(name = "order-service-api")
public interface OrderServiceClient {
    @GetMapping("/api/orders/bring/{orderId}")
    OrderDTO bringOrder(@PathVariable Long orderId);

    @PostMapping("/api/orders/complete/{orderId}")
    OrderDTO completeOrder(@PathVariable Long orderId);
}
