package com.shopping_service_api.Service;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopping_service_api.Client.ProductServiceClient;
import com.shopping_service_api.Client.UserServiceClient;
import com.shopping_service_api.Model.dto.ProductDTO;
import com.shopping_service_api.Model.dto.UserDTO;
import com.shopping_service_api.Model.entity.Cart;
import com.shopping_service_api.Model.entity.CartItem;
import com.shopping_service_api.Repository.CartRepository;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart AddToCart(Long idUser, Long idProduct, Integer quantity) {

        ProductDTO product = productServiceClient.findProductById(idProduct);
        if (product == null) {
            throw new IllegalArgumentException("El producto no existe.");
        }

        Cart cart = cartRepository.findByIdUser(idUser);

        Integer stock = product.getStock();
        if (stock - quantity >= 0) {
            UserDTO user = userServiceClient.getUserById(idUser);

            if (user == null) {
                throw new IllegalArgumentException("El usuario no existe.");
            }

            if (cart == null) {
                cart = Cart.builder()
                        .idUser(user.getId())
                        .email(user.getEmail())
                        .total(0.0)
                        .cartItems(new ArrayList<>())
                        .build();
            }

            CartItem cartItem = cart.getCartItems().stream()
                    .filter(item -> item.getIdProduct().equals(idProduct))
                    .findFirst()
                    .orElse(null);

            if (cartItem != null) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            } else {
                cartItem = CartItem.builder()
                        .idProduct(idProduct)
                        .nameProduct(product.getName())
                        .quantity(quantity)
                        .unitPrice(product.getPrice())
                        .cart(cart)
                        .build();
                cart.getCartItems().add(cartItem);
            }

            productServiceClient.updateStockProduct(idProduct, quantity);

            Double newTotal = cart.getCartItems().stream()
                    .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                    .sum();
            cart.setTotal(newTotal);
        } else {
            throw new IllegalArgumentException("Stock insuficiente.");
        }

        return cartRepository.save(cart);
    }

    @Override
    public Cart RemoveToCart(Long idUser, Long idProduct) {
        Cart cart = cartRepository.findByIdUser(idUser);
        if (cart == null) {
            throw new IllegalArgumentException("El carrito del usuario no existe.");
        }
        CartItem itemToDelete = cart.getCartItems().stream()
                .filter(item -> item.getIdProduct().equals(idProduct))
                .findFirst()
                .orElse(null);
        if (itemToDelete == null) {
            throw new IllegalArgumentException("El producto no está en el carrito.");
        }
        cart.getCartItems().remove(itemToDelete);
        Double newTotal = cart.getCartItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                .sum();
        cart.setTotal(newTotal);
        return cartRepository.save(cart);
    }

    @Override
    public Cart sendCart(Long idUser) {
        Cart cartToSend = cartRepository.findByIdUser(idUser);
        if (cartToSend == null) {
            throw new IllegalArgumentException("El carrito del usuario no existe.");
        }
        return cartToSend;
    }

    @Override
    public void clearCart(Long idUser) {
        Cart cart = cartRepository.findByIdUser(idUser);
        cart.setTotal(0.0);
        cart.getCartItems().clear();
        cartRepository.save(cart);
        }
}

