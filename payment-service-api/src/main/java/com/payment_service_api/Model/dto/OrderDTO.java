package com.payment_service_api.Model.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private String name;
    private String lastName;
    private String email;
    private String role;
    private String address;
    private String phone;
    private List<OrderItemDTO> items;   
    private Double totalAmount;     
    private StatusOrder orderStatus;  
    private LocalDateTime orderDate;

    public enum StatusOrder {
        PENDING, PROCESSING, COMPLETED
    }
}
