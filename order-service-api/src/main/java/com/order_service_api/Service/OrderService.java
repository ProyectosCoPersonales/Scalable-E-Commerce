package com.order_service_api.Service;

import java.util.List;

import com.order_service_api.Model.Entities.Order;
import com.order_service_api.Model.dto.ResponseOrder;

public interface OrderService {
    Order createOrder(Long idUser);
    Order bringOrder(Long orderId);
    Order completeOrder(Long orderId);
    List<ResponseOrder> getOrdersByUser(String email);
    List<ResponseOrder> getAllOrders();
}
