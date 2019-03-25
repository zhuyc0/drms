package com.why.drms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.why.drms.mapper")
public class DrmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrmsApplication.class, args);
    }

}
