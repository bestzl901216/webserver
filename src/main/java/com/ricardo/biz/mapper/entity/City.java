package com.ricardo.webserver.mapper.entity;

import lombok.Data;

/**
 * @author Ricardo
 * @date 2018/12/1
 */
@Data
public class City {

    private Integer id;

    private String name;

    private String countryCode;

    private String district;

    private Integer population;
}
