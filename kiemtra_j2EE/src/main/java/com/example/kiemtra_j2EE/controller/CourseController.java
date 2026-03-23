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



@Controller
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/")
    public ResponseEntity<Map<String, List<Course>>> getAllCoruse(Model model) {
        Map<String, List<Course>> map = new HashMap<>();
        map.put("data", courseRepository.findAll());
        return ResponseEntity.ok(map);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Course>> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseRepository.findById(id);
        Map<String, Course> map = new HashMap<>();
        if (course.isPresent()) {
            map.put("data", course.get());
            return ResponseEntity.ok(map);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course savedCourse = courseRepository.save(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        if (courseRepository.existsById(id)) {
            Course updatedCourse = courseRepository.save(course);
            return ResponseEntity.ok(updatedCourse);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
    if (courseRepository.existsById(id)) {
        courseRepository.deleteById(id);
        return ResponseEntity.noContent().build(); 
    }
    return ResponseEntity.notFound().build();
}
    
}
