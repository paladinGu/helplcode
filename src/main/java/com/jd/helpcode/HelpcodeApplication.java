package com.jd.helpcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "com.jd.helpcode.mapper")
@EnableScheduling
public class HelpcodeApplication {

    public static void main(String[] args) {

        SpringApplication.run(HelpcodeApplication.class, args);
    }

}
