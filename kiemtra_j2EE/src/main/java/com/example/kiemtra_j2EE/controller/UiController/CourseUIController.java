package com.example.kiemtra_j2EE.controller.UiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import  java.util.*;

import com.example.kiemtra_j2EE.models.*;
import com.example.kiemtra_j2EE.repository.*;

@Controller
@RequestMapping("/ui/course")
public class CourseUIController {
    @Autowired
    CourseRepository courseRepository;
    @GetMapping("/")
    public String index(Model model) {
        return "course/index";
    }
}
