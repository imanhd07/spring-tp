package com.example.productsapi.controller;

import com.example.productsapi.dto.*;
import com.example.productsapi.model.Product;
import com.example.productsapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.findAll().stream()
                .map(p -> new ProductResponse(p.getId(), p.getName(), p.getPrice(), p.getDescription()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        Product saved = productService.save(product);
        return new ProductResponse(saved.getId(), saved.getName(), saved.getPrice(), saved.getDescription());
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        Product p = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return new ProductResponse(p.getId(), p.getName(), p.getPrice(), p.getDescription());
    }
}