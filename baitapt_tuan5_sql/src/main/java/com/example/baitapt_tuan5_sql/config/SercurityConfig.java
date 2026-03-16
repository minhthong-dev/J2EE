package com.example.baitapt_tuan5_sql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SercurityConfig {

    public SercurityConfig() {
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Phân quyền cho CategoryController
                .requestMatchers(HttpMethod.GET, "/api/categories/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/categories/**").hasRole("ADMIN")
                
                // Phân quyền cho ProductController
                .requestMatchers(HttpMethod.GET, "/api/products/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/products/**").hasRole("ADMIN")
                
                // Phân quyền cho Giao diện (UI)
                .requestMatchers("/categories-ui/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/products-ui/**").hasAnyRole("USER", "ADMIN")
                
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout.permitAll());
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
