package com.example.jpawebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpaWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaWebAppApplication.class, args);
    }

}
