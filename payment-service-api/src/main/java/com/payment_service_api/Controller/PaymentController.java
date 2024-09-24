package com.payment_service_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.payment_service_api.Feign.Client.NotificationClient;
import com.payment_service_api.Model.dto.OrderDTO;
import com.payment_service_api.Model.dto.PayDTO;
import com.payment_service_api.Service.PaymentService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private NotificationClient notificationClient;

    @GetMapping("/{orderId}")
    private String ViewPayment(@PathVariable Long orderId, Model model) {
        OrderDTO orderDTO = paymentService.ViewOrderDetails(orderId);
        model.addAttribute("order", orderDTO);
        return "index";
    }

    @PostMapping("/payment/create")
    public RedirectView createPayment(
            @RequestParam("orderid") Long orderId,
            @RequestParam("method") String method,
            @RequestParam("amount") String amount,
            @RequestParam("currency") String currency,
            @RequestParam("description") String description) {
        try {
            String amounts = amount.replace(',', '.');
            String cancelUrl = "http://localhost:8084/payment/cancel";
            String successUrl = String.format("http://localhost:8084/payment/success/%d", orderId);
            Payment payment = paymentService.createPayment(
                    orderId,
                    Double.valueOf(amounts),
                    currency,
                    method,
                    "sale",
                    description,
                    cancelUrl,
                    successUrl);

            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return new RedirectView(links.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred:: ", e);
        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("/payment/success/{orderId}")
    private String ViewSuccess(@PathVariable Long orderId, Model model,
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId) {
        OrderDTO orderDTO = paymentService.ViewPaymentSuccess(orderId);
        model.addAttribute("order", orderDTO);
        PayDTO informationToPay = PayDTO.builder().orderId(orderId)
                .userName(orderDTO.getName())
                .userEmail(orderDTO.getEmail())
                .userAddress(orderDTO.getAddress())
                .userPhone(orderDTO.getPhone())
                .orderStatus(orderDTO.getOrderStatus())
                .orderDate(orderDTO.getOrderDate())
                .totalAmount(orderDTO.getTotalAmount())
                .build();
        notificationClient.sendEmail(informationToPay);
        try {
            Payment payment = paymentService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                //notificationClient.sendSms(orderDTO.getPhone(), "Payment Success");
                return "paymentSuccess";
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred:: ", e);
        }
        return "paymentSuccess";

    }

}
