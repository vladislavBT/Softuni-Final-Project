package com.softuni.shoestrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShoestradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoestradeApplication.class, args);
    }

}
