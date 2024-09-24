package com.shopping_service_api.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.shopping_service_api.Model.dto.ProductDTO;

@FeignClient(name = "product-service-api")
public interface ProductServiceClient {

    @GetMapping("/api/product/{idProduct}")
    ProductDTO findProductById(@PathVariable("idProduct") Long idProduct);

    @PutMapping("/api/product/update/stock/{idProduct}")
    ProductDTO updateStockProduct(@PathVariable("idProduct") Long idProduct, @RequestBody Integer Sale);


}
