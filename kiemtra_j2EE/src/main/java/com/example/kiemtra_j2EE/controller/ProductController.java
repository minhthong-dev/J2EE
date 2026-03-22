package com.example.kiemtra_j2EE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.kiemtra_j2EE.models.Product;
import com.example.kiemtra_j2EE.repository.ProductRepository;
import java.util.*;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // lay tat ca
    @GetMapping("/")
    public ResponseEntity<Map<String, List<Product>>> getAllProducts(Model model) {
        Map<String, List<Product>> map = new HashMap<>();
        map.put("data", productRepository.findAll());
        return ResponseEntity.ok(map);
    }

    // lay theo id
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Product>> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        Map<String, Product> map = new HashMap<>();
        if (product.isPresent()) {
            map.put("data", product.get());
            return ResponseEntity.ok(map);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // them moi
    @PostMapping("/")
    public ResponseEntity<Map<String, Product>> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        Map<String, Product> map = new HashMap<>();
        map.put("data", savedProduct);
        return ResponseEntity.ok(map);
    }

    // cap nhat
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        try {
            if (existingProduct.isPresent()) {
                Product updatedProduct = existingProduct.get();
                updatedProduct.setName(product.getName());
                updatedProduct.setPrice(product.getPrice());
                updatedProduct.setCategory(product.getCategory());
                Product savedProduct = productRepository.save(updatedProduct);
                Map<String, Product> map = new HashMap<>();
                map.put("data", savedProduct);
                return ResponseEntity.ok(map);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // xoa
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Product>> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
