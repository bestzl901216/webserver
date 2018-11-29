package com.ricardo.webserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ricardo
 * @date 2018/11/29
 */
@Slf4j
@RestController
public class Controller {

    @GetMapping("test")
    public String test() {
        return "test";
    }
}
