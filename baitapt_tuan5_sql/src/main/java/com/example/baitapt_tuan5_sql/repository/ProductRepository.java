package com.example.baitapt_tuan5_sql.repository;

import com.example.baitapt_tuan5_sql.models.product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<product, Long> {
    Page<product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<product> findByCategoryId(Long categoryId, Pageable pageable);
    Page<product> findByNameContainingIgnoreCaseAndCategoryId(String name, Long categoryId, Pageable pageable);
}