package com.shopping_service_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shopping_service_api.Model.entity.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByIdUser(Long idUser);
}
