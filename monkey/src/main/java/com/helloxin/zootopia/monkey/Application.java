package com.helloxin.zootopia.monkey;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by nandiexin on 2019/1/31.
 */
@SpringBootApplication
@MapperScan("com.helloxin.zootopia.mouse.dao")
@EnableScheduling
@ComponentScan(basePackages = {"com.helloxin.zootopia.**"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
