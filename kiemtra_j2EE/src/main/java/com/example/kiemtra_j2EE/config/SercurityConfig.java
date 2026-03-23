package com.example.kiemtra_j2EE.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/register", "/api/accounts/register", "/css/**", "/js/**", "/images/**").permitAll()
                        
                        // Cấu hình cho Course
                        .requestMatchers(HttpMethod.GET, "/api/course/**").hasAnyRole("STUDENT", "ADMIN")
                        .requestMatchers("/api/course/**").hasRole("ADMIN")
                        
                        // Cấu hình cho Enrollment
                        .requestMatchers(HttpMethod.GET, "/api/enrollment/**").hasAnyRole("STUDENT", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/enrollment/").hasAnyRole("STUDENT", "ADMIN")
                        .requestMatchers("/api/enrollment/**").hasRole("ADMIN")
                    
                        // Cấu hình cho UI
                        .requestMatchers("/ui/course/**").hasAnyRole("STUDENT", "ADMIN")
                        .requestMatchers("/ui/enrollment/**").hasAnyRole("STUDENT", "ADMIN")
                        
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}