package com.example.springfibonnaci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Configuration
@Import({CacheConfiguration.class, SсheduleConfugiration.class})
public class FibonacciApplication {
    public static void main(String[] args) {
        SpringApplication.run(FibonacciApplication.class, args);
    }
}