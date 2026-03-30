package com.example.baitapt_tuan5_sql.repository;

import com.example.baitapt_tuan5_sql.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
