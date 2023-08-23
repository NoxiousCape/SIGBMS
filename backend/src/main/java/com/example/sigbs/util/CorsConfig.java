package com.example.sigbs.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/authenticate")
                .allowedOriginPatterns("http://127.0.0.1:5500") // Cambia la URL según tu frontend
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Content-Type", "Authorization") // Agrega otros encabezados permitidos según tus necesidades
                .exposedHeaders("Authorization")
                .allowCredentials(true);
    }
}