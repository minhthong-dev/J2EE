package com.example.baitapt_tuan5_sql.service;

import com.example.baitapt_tuan5_sql.models.category;
import com.example.baitapt_tuan5_sql.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public category save(category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}