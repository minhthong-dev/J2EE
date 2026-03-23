package com.example.kiemtra_j2EE.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.kiemtra_j2EE.repository.AccountRepository;
import com.example.kiemtra_j2EE.repository.RoleRepository;
import com.example.kiemtra_j2EE.models.Account;
import com.example.kiemtra_j2EE.models.Role;

import com.example.kiemtra_j2EE.models.Product;
import com.example.kiemtra_j2EE.repository.ProductRepository;
import com.example.kiemtra_j2EE.repository.CategoryRepository;
import com.example.kiemtra_j2EE.repository.CourseRepository;
import com.example.kiemtra_j2EE.models.Category;
import com.example.kiemtra_j2EE.models.Course;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initData(AccountRepository accountRepository,
            RoleRepository roleRepository,
            CategoryRepository categoryRepository,
            ProductRepository productRepository,
            CourseRepository courseRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {

            // khoi tao du lieu category
            if (categoryRepository.findByName("Electronics").isEmpty()) {
                Category category = new Category();
                category.setName("Electronics");
                categoryRepository.save(category);
            }

            // khoi tao du lieu course
             if (courseRepository.findByName("Triet Hoc Mac-LeNin").isEmpty()) {
                Course course = new Course();
                course.setName("Triet Hoc Mac-LeNin");
                course.setCredits(200.0);
                course.setLecture("Nguyen Van A");
                course.setUrlImage("chua co");
                course.setCategory(categoryRepository.findByName("Dai Cuong").get());
                courseRepository.save(course);
            }
            // khoi tao du lieu products

            if (productRepository.findByName("Product 1").isEmpty()) {
                Product product = new Product();
                product.setName("Product 1");
                product.setPrice(Double.valueOf(100));
                product.setCategory(categoryRepository.findByName("Electronics").get());
                productRepository.save(product);
            }

            // khoi toi du lieu user
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setName("ADMIN");
                        return roleRepository.save(r);
                    });
            Role userRole = roleRepository.findByName("USER")
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setName("USER");
                        return roleRepository.save(r);
                    });
            Role studentRole = roleRepository.findByName("STUDENT")
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setName("STUDENT");
                        return roleRepository.save(r);
                    });

            if (accountRepository.findByUsername("admin").isEmpty()) {
                Account admin = new Account();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setEmail("admin@example.com");
                admin.setRoles(Set.of(adminRole));
                accountRepository.save(admin);
            }

            if (accountRepository.findByUsername("user").isEmpty()) {
                Account user = new Account();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("123456"));
                user.setEmail("user@example.com");
                user.setRoles(Set.of(userRole));
                accountRepository.save(user);
            }

        };
    }
}
