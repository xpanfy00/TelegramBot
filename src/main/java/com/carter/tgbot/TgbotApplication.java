package com.carter.tgbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TgbotApplication {

    public static void main(String[] args) {
        System.out.println("PGHOST: " + System.getenv("PGHOST"));
        System.out.println("PGPORT: " + System.getenv("PGPORT"));
        System.out.println("PGDATABASE: " + System.getenv("PGDATABASE"));
        System.out.println("POSTGRES_USER: " + System.getenv("POSTGRES_USER"));
        System.out.println("POSTGRES_PASSWORD: " + System.getenv("POSTGRES_PASSWORD"));
        SpringApplication.run(TgbotApplication.class, args);
    }

}
