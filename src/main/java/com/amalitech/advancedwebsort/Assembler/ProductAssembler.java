
package com.amalitech.advancedwebsort.Assembler;

import com.amalitech.advancedwebsort.Controllers.ProductController;
import com.amalitech.advancedwebsort.Entity.Product;
import com.amalitech.advancedwebsort.Requests.ProductRequest;
import com.amalitech.advancedwebsort.Service.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//@Component
//@RequiredArgsConstructor
//public class ProductAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {
//    private final ProductService productService;
//   // private final ProductRequest productRequest;
//
//    @Override
////    public EntityModel<Product> toModel( Product product) {
////        return EntityModel.of(productService.createProduct(productRequest),
////                linkTo(methodOn(ProductController.class).createProduct(productRequest)).withSelfRel(),
////                linkTo(methodOn(ProductController.class).getAll(productRequest)).withRel("create-product"));
////    }
//}

