package org.example.chat.room.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableTransactionManagement
@EnableSwagger2
@MapperScan("org.example.chat.room.dao")
@SpringBootApplication(scanBasePackages = {"org.example.chat.room.api", "org.example.chat.room.dao", "org.example.chat.room.rest"})
public class RestLauncher {
    public static void main(String[] args) {
        SpringApplication.run(RestLauncher.class, args);
    }
}
