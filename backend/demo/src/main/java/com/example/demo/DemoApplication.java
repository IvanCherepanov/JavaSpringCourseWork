package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    //сразу не надо security добавлять, иначе будет блокать запросы
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
