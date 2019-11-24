package com.MARKITN.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MarkiTnApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarkiTnApplication.class, args);

    }

}
