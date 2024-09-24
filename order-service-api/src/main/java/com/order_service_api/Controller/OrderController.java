package com.order_service_api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order_service_api.Model.Entities.Order;
import com.order_service_api.Model.dto.ResponseOrder;
import com.order_service_api.Service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create/{idUser}")
    public ResponseEntity<?> createOrder(@PathVariable Long idUser) {
        try {
            Order newOrder = orderService.createOrder(idUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la orden: " + e.getMessage());
        }
    }

    @GetMapping("/bring/{orderId}")
    public ResponseEntity<?> bringOrder(@PathVariable Long orderId) {
        try {
            Order order = orderService.bringOrder(orderId);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Orden no encontrada: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al traer la orden: " + e.getMessage());
        }
    }
    
    @PostMapping("/complete/{orderId}")
    public ResponseEntity<?> completeOrder(@PathVariable Long orderId) {
        try {
            Order order = orderService.completeOrder(orderId);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Orden no encontrada: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al completar la orden: " + e.getMessage());
        }
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> getOrdersByUser(@PathVariable String email) {
        try {
            List<ResponseOrder> responseOrders = orderService.getOrdersByUser(email);
            if (responseOrders == null || responseOrders.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron órdenes para el usuario: " + email);
            }
            return ResponseEntity.ok(responseOrders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener órdenes del usuario: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders() {
        try {
            List<ResponseOrder> responseOrders = orderService.getAllOrders();
            if (responseOrders == null || responseOrders.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron órdenes.");
            }
            return ResponseEntity.ok(responseOrders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener todas las órdenes: " + e.getMessage());
        }
    }
}
