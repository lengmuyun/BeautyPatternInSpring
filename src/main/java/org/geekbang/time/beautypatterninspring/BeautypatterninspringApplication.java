package org.geekbang.time.beautypatterninspring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.geekbang.time.beautypatterninspring.mapper")
public class BeautypatterninspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeautypatterninspringApplication.class, args);
    }

}
