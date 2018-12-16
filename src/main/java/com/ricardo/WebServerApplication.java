package com.ricardo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @author Ricardo
 * @date 2018/11/29
 */
@SpringBootApplication
@MapperScan(value = {"com.ricardo.account.mapper", "com.ricardo.biz.mapper"})
@Slf4j
public class WebServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebServerApplication.class, args);
    }
}

