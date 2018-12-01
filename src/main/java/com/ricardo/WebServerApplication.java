package com.ricardo.webserver;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Ricardo
 * @date 2018/11/29
 */
@SpringBootApplication
@MapperScan("com.ricardo.webserver.mapper")
@Slf4j
public class WebServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebServerApplication.class, args);
    }
}
