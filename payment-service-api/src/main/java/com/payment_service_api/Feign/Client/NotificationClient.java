package com.payment_service_api.Feign.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.payment_service_api.Model.dto.PayDTO;

@FeignClient(name = "notification-service-api")
public interface NotificationClient {

    @PostMapping("/api/notifications/email")
    void sendEmail(@RequestBody PayDTO payDTO);

    @PostMapping("/api/notifications/sms")
    void sendSms(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("message") String message);
}

