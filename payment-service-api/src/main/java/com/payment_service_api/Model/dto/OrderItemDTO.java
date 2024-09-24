package com.payment_service_api.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private Long idProduct;
    private String nameProduct;
    private Integer quantity;
    private Double unitPrice;   
    private Double totalPrice;    
}

