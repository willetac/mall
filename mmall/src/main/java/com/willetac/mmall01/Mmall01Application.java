package com.willetac.mmall01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.willetac.mmall01.dao")
public class Mmall01Application {

    public static void main(String[] args) {
        SpringApplication.run(Mmall01Application.class, args);
    }

}

