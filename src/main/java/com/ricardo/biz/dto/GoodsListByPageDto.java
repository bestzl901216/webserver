package com.ricardo.biz.dto;

import com.ricardo.biz.mapper.entity.Goods;
import com.ricardo.common.BasePageDto;
import lombok.Data;

/**
 * @author Ricardo
 * @date 2018/12/16
 */
@Data
public class GoodsListByPageDto extends BasePageDto {

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
