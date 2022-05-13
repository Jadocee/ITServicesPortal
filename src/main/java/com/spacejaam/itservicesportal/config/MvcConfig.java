package com.spacejaam.itservicesportal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("$scripts/**")
        .addResourceLocations("/WEB-INF/scripts/")
        .setCachePeriod(31556926);
    registry
        .addResourceHandler("$styles/**")
        .addResourceLocations("/WEB-INF/styles/")
        .setCachePeriod(31556926);
    registry
        .addResourceHandler("$assets/**")
        .addResourceLocations("/WEB-INF/assets/")
        .setCachePeriod(31556926);
  }
}
