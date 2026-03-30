package com.example.baitapt_tuan5_sql.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Expose the "uploads" directory in the project root to the /images/ path
        String uploadPath = Paths.get(System.getProperty("user.dir") + "/uploads").toFile().getAbsolutePath();
        registry.addResourceHandler("/images/**").addResourceLocations("file:/" + uploadPath + "/");
    }
}
