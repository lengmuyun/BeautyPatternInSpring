package org.geekbang.time.beautypatterninspring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("org.geekbang.time.beautypatterninspring.mapper")
@EnableAsync
public class BeautypatterninspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeautypatterninspringApplication.class, args);
    }

}
