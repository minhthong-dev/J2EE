package com.example.baitapt_tuan5_sql.controller;

import com.example.baitapt_tuan5_sql.models.category;
import com.example.baitapt_tuan5_sql.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<category> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<category> getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public category createCategory(@RequestBody category category) {
        return categoryService.save(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<category> updateCategory(@PathVariable Long id, @RequestBody category categoryDetails) {
        return categoryService.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(categoryDetails.getName());
                    category updatedCategory = categoryService.save(existingCategory);
                    return ResponseEntity.ok(updatedCategory);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryService.findById(id).isPresent()) {
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}