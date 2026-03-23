package com.example.kiemtra_j2EE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

import com.example.kiemtra_j2EE.models.Account;
import com.example.kiemtra_j2EE.models.Enrollment;


@Repository
public interface  EnrollmentRepository extends JpaRepository<Enrollment, Long> {
     Optional<Enrollment> findById(Long id);
     List<Enrollment> findByAccountId(Long accountId);
     List<Enrollment> findByCourseId(Long courseId);
}
