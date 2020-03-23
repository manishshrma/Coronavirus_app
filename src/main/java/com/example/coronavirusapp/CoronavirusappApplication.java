package com.example.coronavirusapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronavirusappApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoronavirusappApplication.class, args);
    }

}
