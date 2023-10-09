package com.divide.experience.article.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Anton Oleynikov {@literal <antonleynikov@gmail.com>}
 */
@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name())
            .allowedHeaders("*")
            .allowCredentials(true)
            .exposedHeaders("Authorization")
            .maxAge(3600);
    }
}
