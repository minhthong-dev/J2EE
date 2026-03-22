package com.example.kiemtra_j2EE.controller;

// import com.example.kiemtra_j2EE.models.product;
// import com.example.kiemtra_j2EE.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.*;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    @GetMapping("/hello")
    public String home() {
        // Map<String, String> map = new HashMap<>();
        // map.put("message", "m da dang nhap thanh cong");
        // return ResponseEntity.ok(map);
        return "home/index";
    }
}
