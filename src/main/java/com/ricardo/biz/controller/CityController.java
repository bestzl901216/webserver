package com.ricardo.biz.controller;

import com.ricardo.biz.mapper.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ricardo
 * @date 2018/11/29
 */
@Slf4j
@RestController
@RequestMapping("city")
public class CityController {

    @GetMapping("/{id}")
    public City test(@PathVariable Integer id) {
        return new City(id, "北京", "beijing", "", 10000000);
    }
}
