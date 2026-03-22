package com.example.kiemtra_j2EE.controller.UiController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category-ui")
public class CategoryUIController {
    @GetMapping("/")
    public String index() {
        return "category/index";
    }
}
