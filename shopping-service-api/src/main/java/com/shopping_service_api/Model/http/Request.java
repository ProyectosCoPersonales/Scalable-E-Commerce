package com.shopping_service_api.Model.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private Long idUser;
    private Long idProduct;
    private Integer quantity;
}
