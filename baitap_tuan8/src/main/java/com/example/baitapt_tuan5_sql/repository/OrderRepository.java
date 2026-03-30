package com.example.baitapt_tuan5_sql.repository;

import com.example.baitapt_tuan5_sql.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
