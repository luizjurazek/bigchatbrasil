package com.big_chat_brasil.bigchatbrasil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableJpaRepositories("com.big_chat_brasil.bigchatbrasil.*")
@ComponentScan(basePackages = { "com.big_chat_brasil.bigchatbrasil.*" })
@EntityScan("com.big_chat_brasil.bigchatbrasil.*")   

public class BigchatbrasilApplication {
    public static void main(String[] args) {
        SpringApplication.run(BigchatbrasilApplication.class, args);
    }
}


