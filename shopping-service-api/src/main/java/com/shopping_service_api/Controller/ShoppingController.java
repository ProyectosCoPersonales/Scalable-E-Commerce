package com.shopping_service_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shopping_service_api.Model.entity.Cart;
import com.shopping_service_api.Model.http.Request;
import com.shopping_service_api.Service.ShoppingService;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody Request request) {
        try {
            Cart cart = shoppingService.AddToCart(request.getIdUser(), request.getIdProduct(), request.getQuantity());
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al añadir producto al carrito: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove-from-cart")
    public ResponseEntity<?> removeFromCart(@RequestBody Request request) {
        try {
            Cart cart = shoppingService.RemoveToCart(request.getIdUser(), request.getIdProduct());
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al remover producto del carrito: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/send-cart/{idUser}")
    public ResponseEntity<?> sendCart(@PathVariable Long idUser) {
        try {
            Cart cart = shoppingService.sendCart(idUser);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al enviar el carrito: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/clear-cart/{idUser}")
    public void cleanCart(@PathVariable Long idUser){
        shoppingService.clearCart(idUser);
    }
}
