package com.example.kiemtra_j2EE.controller.UiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kiemtra_j2EE.models.Account;
import com.example.kiemtra_j2EE.repository.AccountRepository;
import com.example.kiemtra_j2EE.repository.EnrollmentRepository;

@Controller
@RequestMapping("/ui/enrollment")   
public class EnrollmentUIController {
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        
        model.addAttribute("enrollments", enrollmentRepository.findByAccountId(account.getId()));
        return "enrollment/index";
    }
}
