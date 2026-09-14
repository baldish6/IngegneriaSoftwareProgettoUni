package com.unicam.hackhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tools.jackson.databind.JsonNode;

import java.util.function.Consumer;

@SpringBootApplication
public class HackHubApplication {

    /* @Bean
    public Consumer<JsonNode> fixSpringBug() {
        return value -> {};
    }*/

    public static void main(String[] args) {
        SpringApplication.run(HackHubApplication.class, args);
    }

}
