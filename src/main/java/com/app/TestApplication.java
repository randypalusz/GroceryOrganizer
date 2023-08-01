package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaRepositories("com.app.repositories")
@EntityScan({"com.app.models", "com.app.converters"})
@SpringBootApplication
public class TestApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
