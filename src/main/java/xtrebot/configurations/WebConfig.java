package xtrebot.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/")  // Permet toutes les URL
                .allowedOrigins("*")  // Permet toutes les origines
                .allowedMethods("*")  // Permet toutes les méthodes HTTP (GET, POST, etc.)
                .allowedHeaders("*"); // Permet tous les en-têtes

    }
}