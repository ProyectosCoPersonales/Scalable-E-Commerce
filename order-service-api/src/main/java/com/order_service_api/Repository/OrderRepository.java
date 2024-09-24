package com.order_service_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.order_service_api.Model.Entities.Order;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByEmail(String email);
}
