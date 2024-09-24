package com.product_service_api.Service;

import java.util.List;
import com.product_service_api.Entity.Product;

public interface ProductService {
    List<Product> findAllProducts();
    Product updateStockProduct(Long idProduct, Integer Stock);
    Product findProductById(Long idProduct);
    Product saveProduct(Product product);
    List<Product> saveListProducts(List<Product> products);
    
}
