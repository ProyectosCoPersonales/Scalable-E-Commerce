package com.notification_service_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notification_service_api.Service.EmailService;
import com.notification_service_api.Service.TwilioSendSmsService;
import com.notification_service_api.dto.PayDTO;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final EmailService emailService;
    private final TwilioSendSmsService smsService;

    public NotificationController(EmailService emailService, TwilioSendSmsService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendOrderConfirmationEmail(@RequestBody PayDTO payDTO) {
        try {
            emailService.sendEmail(payDTO);
            return ResponseEntity.ok("Email enviado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar el email: " + e.getMessage());
        }
    }

    @PostMapping("/sms")
    public ResponseEntity<String> sendSms(@RequestParam String phoneNumber, @RequestParam String message) {
        try {
            smsService.sendSms(phoneNumber, message);
            return ResponseEntity.ok("SMS enviado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar el SMS: " + e.getMessage());
        }
    }
}

