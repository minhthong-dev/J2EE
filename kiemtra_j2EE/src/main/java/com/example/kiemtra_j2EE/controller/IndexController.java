package com.example.kiemtra_j2EE.controller;

// import com.example.kiemtra_j2EE.models.product;
// import com.example.kiemtra_j2EE.service.ProductService;
import com.example.kiemtra_j2EE.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class IndexController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "home/index";
    }

    @GetMapping("/hello")
    public String home() {
        return "home/index";
    }

    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    @GetMapping("/register")
    public String register() {
        return "home/register";
    }
}
