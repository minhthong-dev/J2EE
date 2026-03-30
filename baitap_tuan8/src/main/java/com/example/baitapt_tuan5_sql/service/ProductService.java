package com.example.baitapt_tuan5_sql.service;

import com.example.baitapt_tuan5_sql.models.product;
import com.example.baitapt_tuan5_sql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<product> findAll() {
        return productRepository.findAll();
    }

    public Page<product> searchAndFilter(String name, Long categoryId, Pageable pageable) {
        if (name != null && !name.isEmpty() && categoryId != null) {
            return productRepository.findByNameContainingIgnoreCaseAndCategoryId(name, categoryId, pageable);
        } else if (name != null && !name.isEmpty()) {
            return productRepository.findByNameContainingIgnoreCase(name, pageable);
        } else if (categoryId != null) {
            return productRepository.findByCategoryId(categoryId, pageable);
        }
        return productRepository.findAll(pageable);
    }

    public Optional<product> findById(Long id) {
        return productRepository.findById(id);
    }

    public product save(product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}