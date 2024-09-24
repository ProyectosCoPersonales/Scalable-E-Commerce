package com.order_service_api.Model.dto;

import java.time.LocalDateTime;
import com.order_service_api.Model.Entities.Order.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrder {
    private Long orderId;
    private String email;
    private Double totalAmount;
    private StatusOrder orderStatus;
    private LocalDateTime orderDate;
}
