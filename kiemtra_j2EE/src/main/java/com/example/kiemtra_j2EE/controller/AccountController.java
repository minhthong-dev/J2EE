package com.example.kiemtra_j2EE.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.kiemtra_j2EE.models.Account;
import com.example.kiemtra_j2EE.models.Role;
import com.example.kiemtra_j2EE.repository.*;

@Controller
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerStudent(@RequestBody Account account) {
        Map<String, String> map = new HashMap<>();
        
        if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
            map.put("message", "Tên đăng nhập đã tồn tại");
            return ResponseEntity.badRequest().body(map);
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        Role userRole = roleRepository.findByName("STUDENT")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        account.setRoles(Set.of(userRole));
        
        accountRepository.save(account);
        
        map.put("message", "Đăng ký thành công!");
        return ResponseEntity.ok(map);
    }
}
