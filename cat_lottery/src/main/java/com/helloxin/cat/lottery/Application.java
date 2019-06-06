package com.helloxin.cat.lottery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 */
@SpringBootApplication
@MapperScan("com.helloxin.zootopia.mouse.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
