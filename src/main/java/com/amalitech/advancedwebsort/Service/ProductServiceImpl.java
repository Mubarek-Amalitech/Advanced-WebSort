package com.amalitech.advancedwebsort.Service;

import com.amalitech.advancedwebsort.Entity.Product;
import com.amalitech.advancedwebsort.Repository.ProductRepository;
import com.amalitech.advancedwebsort.Requests.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements  ProductService {
    private final ProductRepository productRepository;
    Product product;
    public Product createProduct(ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.name()).quantity(productRequest.quantity()).productId(productRequest.productId()).build();
        return productRepository.save(product);
    }

    public void deleteProduct(String productId) {
        Optional<Product> optionalProduct = productRepository.findByProductId(productId);
        optionalProduct.ifPresentOrElse(productRepository::delete, () -> {
            throw new RuntimeException(" product not found");
        });
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findByProductId(productRequest.productId());
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException(" product not found");
        }
        var product = optionalProduct.get();
        product.setName(productRequest.name());
       product.setQuantity(productRequest.quantity());
        productRepository.save(product);
        return product;
    }
    public Product getProduct(String productId) {
        Optional<Product> optionalProduct = productRepository.findByProductId(productId);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("product not found");
        }
        return optionalProduct.get();
    }
}
