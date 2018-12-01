package com.ricardo.webserver.controller;

import com.ricardo.webserver.mapper.CityMapper;
import com.ricardo.webserver.mapper.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CityMapper cityMapper;

    @GetMapping("/{id}")
    public City test(@PathVariable Integer id) {
        return cityMapper.getById(id);
    }
}
