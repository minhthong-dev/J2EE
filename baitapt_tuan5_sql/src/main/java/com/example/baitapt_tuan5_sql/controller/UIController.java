package com.example.baitapt_tuan5_sql.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/products")
    public String productsPage() {
        return "products";
    }

    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }

    @GetMapping("/categories-ui")
    public String categoriesPage() {
        return "categories";
    }
}
