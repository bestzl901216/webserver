package com.ricardo.biz.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Ricardo
 * @date 2018/12/10
 */
@Data
public class GoodsCreateDto {

    /** 名称 **/
    @NotBlank
    private String name;
    /** 商品规格 **/
    @NotBlank
    private String specification;
    /** 厂商 **/
    @NotBlank
    private String manufacturer;

}
