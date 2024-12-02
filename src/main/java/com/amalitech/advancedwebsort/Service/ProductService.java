package com.amalitech.advancedwebsort.Service;

import com.amalitech.advancedwebsort.Entity.Product;
import com.amalitech.advancedwebsort.Requests.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {



    Product createProduct(ProductRequest productRequest);
    Product getProduct(String productId);
     Product  updateProduct(ProductRequest productRequest);
     List<Product> getAllProducts();
    void deleteProduct(String productId);
}
