package com.shopping_service_api.Service;

import com.shopping_service_api.Model.entity.Cart;

public interface ShoppingService {
    Cart AddToCart(Long idUser,Long idProduct, Integer quantity);
    Cart RemoveToCart(Long idUser, Long idProduct);
    Cart sendCart(Long idUser);
    void clearCart(Long idUser);
}
