package com.payment_service_api.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.payment_service_api.Model.dto.OrderDTO.StatusOrder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayDTO {
    private Long orderId;
    private String userName;
    private String userEmail;
    private String userAddress;
    private String userPhone;
    private StatusOrder orderStatus;
    private LocalDateTime orderDate;
    private Double totalAmount;
}
