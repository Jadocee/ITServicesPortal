package com.spacejaam.itservicesportal.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(value = "com.spacejaam.itservicesportal")
@Import(value = SecurityConfig.class)
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("$scripts/**")
                .addResourceLocations("/WEB-INF/scripts/", "../WEB-INF/scripts/")
                .setCachePeriod(31556926);
        registry
                .addResourceHandler("$styles/**")
                .addResourceLocations("/WEB-INF/styles/", "../WEB-INF/styles/", "")
                .setCachePeriod(31556926);
        registry
                .addResourceHandler("$assets/**")
                .addResourceLocations("/WEB-INF/assets/", "../WEB-INF/assets/")
                .setCachePeriod(31556926);
    }
}


