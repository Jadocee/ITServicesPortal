package com.spacejaam.itservicesportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;


@SpringBootApplication(scanBasePackages = "com.spacejaam.itservicesportal")
@EnableJdbcRepositories(basePackages = "com.spacejaam.itservicesportal")
public class ItServicesPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItServicesPortalApplication.class, args);
    }

}
