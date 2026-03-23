package com.example.kiemtra_j2EE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.example.kiemtra_j2EE.models.Account;
import com.example.kiemtra_j2EE.models.Course;


@Repository
public interface  CourseRepository extends JpaRepository<Course, Long> {
     Optional<Course> findById(Long id);
     Optional<Course> findByName(String name);
}
