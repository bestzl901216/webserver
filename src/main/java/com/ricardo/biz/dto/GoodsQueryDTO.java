package com.ricardo.biz.dto;

import com.ricardo.biz.mapper.entity.Goods;
import lombok.Data;

/**
 * @author Ricardo
 * @date 2018/12/16
 */
@Data
public class GoodsQueryDTO {

    private Integer id;
    /** 名称 **/
    private String name;
    /** 商品规格 **/
    private String specification;
    /** 厂商 **/
    private String manufacturer;
    /** 商品状态 **/
    private Goods.StatusEnum status;
}
