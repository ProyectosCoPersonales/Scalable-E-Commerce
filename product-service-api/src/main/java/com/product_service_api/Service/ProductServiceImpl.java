package com.product_service_api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.product_service_api.Entity.Product;
import com.product_service_api.Repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            return null;
        }

    }
    @Override
    public Product findProductById(Long idProduct) {
        try {
            return productRepository.findById(idProduct).get();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Product saveProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public List<Product> saveListProducts(List<Product> products) {
        try {
            return productRepository.saveAll(products);
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public Product updateStockProduct(Long idProduct, Integer sale) {
        try {
            Product product = productRepository.findById(idProduct).get();
            Integer stock = product.getStock();
            product.setStock(stock-sale);
            return productRepository.save(product);
        } catch (Exception e) {
            return null;
        }
    }

}
