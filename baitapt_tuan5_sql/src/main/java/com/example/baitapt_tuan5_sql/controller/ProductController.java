package com.example.baitapt_tuan5_sql.controller;

import com.example.baitapt_tuan5_sql.models.product;
import com.example.baitapt_tuan5_sql.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @GetMapping
    public List<product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<product> getProductById(@PathVariable Long id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public product createProduct(@RequestParam("name") String name,
            @RequestParam("price") Double price,
            @RequestParam(value = "file", required = false) org.springframework.web.multipart.MultipartFile file)
            throws java.io.IOException {
        product p = new product();
        p.setName(name);
        p.setPrice(price);
        if (file != null && !file.isEmpty()) {
            java.nio.file.Path uploadPath = java.nio.file.Paths.get(UPLOAD_DIRECTORY);
            if (!java.nio.file.Files.exists(uploadPath)) {
                java.nio.file.Files.createDirectories(uploadPath);
            }
            String fileName = java.util.UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            java.nio.file.Path filePath = uploadPath.resolve(fileName);
            java.nio.file.Files.write(filePath, file.getBytes());
            p.setPath("/images/" + fileName);
        }
        return productService.save(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<product> updateProduct(@PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("price") Double price,
            @RequestParam(value = "file", required = false) org.springframework.web.multipart.MultipartFile file) {
        return productService.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(name);
                    existingProduct.setPrice(price);
                    if (file != null && !file.isEmpty()) {
                        try {
                            java.nio.file.Path uploadPath = java.nio.file.Paths.get(UPLOAD_DIRECTORY);
                            if (!java.nio.file.Files.exists(uploadPath)) {
                                java.nio.file.Files.createDirectories(uploadPath);
                            }
                            String fileName = java.util.UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                            java.nio.file.Path filePath = uploadPath.resolve(fileName);
                            java.nio.file.Files.write(filePath, file.getBytes());
                            existingProduct.setPath("/images/" + fileName);
                        } catch (java.io.IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    product updatedProduct = productService.save(existingProduct);
                    return ResponseEntity.ok(updatedProduct);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.findById(id).isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}