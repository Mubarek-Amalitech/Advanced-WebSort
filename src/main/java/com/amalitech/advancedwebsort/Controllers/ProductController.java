package com.amalitech.advancedwebsort.Controllers;
import com.amalitech.advancedwebsort.Entity.Product;
import com.amalitech.advancedwebsort.Requests.ProductRequest;
import com.amalitech.advancedwebsort.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;
    @PostMapping("/create-product")
  public   EntityModel<Product> createProduct(@RequestBody ProductRequest productRequest) {
         var product = productService.createProduct(productRequest);
        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).createProduct(productRequest)).withSelfRel(),
                linkTo(methodOn(ProductController.class).getProduct(product.getProductId())).withRel("single product"),
                linkTo(methodOn(ProductController.class).getAll()).withRel("get-all"));
    }

    @GetMapping("/get-all")
     public CollectionModel<EntityModel<Product>> getAll() {
        List<EntityModel<Product>> products = productService.getAllProducts().stream().map(product -> EntityModel.of(product,
                linkTo(methodOn(ProductController.class).getAll()).withSelfRel(),
                linkTo(methodOn(ProductController.class).deleteProduct(product.getProductId())).withRel("/delete-product/{productId}"),
                linkTo(methodOn(ProductController.class).getProduct(product.getProductId())).withRel("single-product/{productId}"),
                linkTo(methodOn(ProductController.class).getAll()).withRel("get-all"))).
                toList();

        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).getAll()).withSelfRel());
    }

   public @PatchMapping("/update-product")
    EntityModel<Product> updateProduct ( @ RequestBody ProductRequest productRequest) {
        return EntityModel.of(productService.updateProduct(productRequest),
                linkTo(methodOn(ProductController.class).updateProduct(productRequest)).withSelfRel(),
                linkTo(methodOn(ProductController.class)).withRel("update-product"));
    }

     @GetMapping("/single-product/{productId}")
     public EntityModel< Product> getProduct (@RequestParam String productId) {
           return  EntityModel.of(productService.getProduct(productId),
                   linkTo(methodOn(ProductController.class).getProduct(productId)).withSelfRel(),
                   linkTo(methodOn(ProductController.class).getAll()).withRel("get-all"));
     }

    @DeleteMapping("/delete-product/{productId}")
    ResponseEntity<?> deleteProduct(@RequestParam String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.accepted().build();
    }

}
