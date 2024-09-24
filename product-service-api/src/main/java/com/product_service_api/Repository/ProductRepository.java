package com.product_service_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.product_service_api.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}