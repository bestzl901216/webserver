package com.ricardo.biz.vo;

import com.ricardo.biz.mapper.entity.Goods;
import lombok.Data;

/**
 * @author Ricardo
 * @date 2018/12/16
 */
@Data
public class GoodsVO {

    /** 名称 **/
    private String name;
    /** 商品规格 **/
    private String specification;
    /** 厂商 **/
    private String manufacturer;
    /** 商品状态 **/
    private Goods.StatusEnum status;

}
