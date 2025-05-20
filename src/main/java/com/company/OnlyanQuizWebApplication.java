package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.company")
@EnableJpaRepositories(basePackages = "com.company")
public class OnlyanQuizWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlyanQuizWebApplication.class, args);


    }

}
