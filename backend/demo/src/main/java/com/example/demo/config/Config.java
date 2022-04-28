package com.example.demo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.demo.model.dao")
@ComponentScan(basePackages = {"com.example.demo"})
@EntityScan("com.example.demo")
@EnableAspectJAutoProxy
@EnableAsync
@EnableScheduling
public class Config {
}
