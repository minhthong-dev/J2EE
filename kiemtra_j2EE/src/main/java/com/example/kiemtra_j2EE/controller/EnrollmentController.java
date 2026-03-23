package com.example.kiemtra_j2EE.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import  java.util.*;

import com.example.kiemtra_j2EE.models.*;
import com.example.kiemtra_j2EE.repository.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/api/enrollment")
public class EnrollmentController {
    @Autowired
    EnrollmentRepository enrollmentRepository;
    
    @Autowired
    AccountRepository accountRepository;
    
    @GetMapping("/")
    public ResponseEntity<Map<String, List<Enrollment>>> getAllEnrollment(Model model) {
        Map<String, List<Enrollment>> map = new HashMap<>();
        map.put("data", enrollmentRepository.findAll());
        return ResponseEntity.ok(map);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Enrollment>> getEnrollmentById(@PathVariable Long id) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
        Map<String, Enrollment> map = new HashMap<>();
        if (enrollment.isPresent()) {
            map.put("data", enrollment.get());
            return ResponseEntity.ok(map);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/account/{accountId}")
    public ResponseEntity<Map<String, List<Enrollment>>> getEnrollmentByAccountId(@PathVariable Long accountId) {
        List<Enrollment> enrollments = enrollmentRepository.findByAccountId(accountId);
        Map<String, List<Enrollment>> map = new HashMap<>();
        map.put("data", enrollments);
        return ResponseEntity.ok(map);
    }
    @PostMapping("/")
    public ResponseEntity<?> createEnrollment(@RequestBody Enrollment enrollment) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getName().equals("anonymousUser")) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Vui lòng đăng nhập"));
        }

        String username = auth.getName();
        Optional<Account> accOpt = accountRepository.findByUsername(username);
        
        if (accOpt.isPresent()) {
            Account account = accOpt.get();
            // Kiểm tra xem đã đăng ký chưa
            List<Enrollment> existing = enrollmentRepository.findByAccountId(account.getId());
            boolean alreadyEnrolled = existing.stream().anyMatch(e -> e.getCourse().getId().equals(enrollment.getCourse().getId()));
            if (alreadyEnrolled) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Bạn đã đăng ký học phần này rồi!"));
            }

            enrollment.setAccount(account);
            Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEnrollment);
        } else {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Người dùng không tồn tại"));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        if (enrollmentRepository.existsById(id)) {
            Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
            return ResponseEntity.ok(updatedEnrollment);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
    if (enrollmentRepository.existsById(id)) {
        enrollmentRepository.deleteById(id);
        return ResponseEntity.noContent().build(); 
    }
    return ResponseEntity.notFound().build();
}
    
}
