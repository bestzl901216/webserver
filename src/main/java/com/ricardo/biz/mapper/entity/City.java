package com.ricardo.biz.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ricardo
 * @date 2018/12/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    private Integer id;

    private String name;

    private String countryCode;

    private String district;

    private Integer population;
}
