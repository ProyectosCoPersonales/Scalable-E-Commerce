package com.order_service_api.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long id;
    private Long idProduct;
    private String nameProduct;
    private Integer quantity;
    private Double unitPrice;
}

