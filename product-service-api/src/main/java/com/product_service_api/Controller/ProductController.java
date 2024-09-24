package com.product_service_api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.product_service_api.Authorization.Client.AuthServiceClient;
import com.product_service_api.Entity.Product;
import com.product_service_api.Service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthServiceClient authServiceClient;

    @GetMapping
    public ResponseEntity<?> findAllProducts() {
        if (authServiceClient.IsUser()) {
            try {
                return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Your are not user", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/update/stock/{idProduct}")
    public ResponseEntity<?> updateStockProduct(@PathVariable("idProduct") Long idProduct, @RequestBody Integer Stock) {
        if (authServiceClient.IsUser()) {
            try {
                return new ResponseEntity<>(productService.updateStockProduct(idProduct, Stock), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Your are not user", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        if (authServiceClient.isAdmin()) {
            try {
                return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
            }
        } else {
            return null;
        }

    }

    @PostMapping("/save/list")
    public ResponseEntity<?> saveListProducts(@RequestBody List<Product> products) {
        if (authServiceClient.isAdmin()) {
            try {
                return new ResponseEntity<>(productService.saveListProducts(products), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
            }
        } else {
            return null;
        }
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<?> findProductById(@PathVariable("idProduct") Long idProduct) {
        if (authServiceClient.IsUser()) {
            try {
                return new ResponseEntity<>(productService.findProductById(idProduct), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Your are not user", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
