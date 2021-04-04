package com.honner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.honner.mapper")
public class SpringbootEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEduApplication.class, args);
    }

}
