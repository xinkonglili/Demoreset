package com.nk;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nk.mapper")
public class DemoresetApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoresetApplication.class, args);
    }

}
