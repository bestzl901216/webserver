package com.ricardo.biz.mapper.entity;

import com.ricardo.common.BaseEntity;
import lombok.Data;

/**
 * @author Ricardo
 * @date 2018/12/12
 */
@Data
public class VoucherCategory extends BaseEntity {

    /** 礼品券种类id **/
    private Integer id;
    /** 商品id,外键连接Goods表 **/
    private Integer goodsId;
    /** 商品数量 **/
    private Integer goodsQuantity;
    /** 礼品券种类状态 **/
    private Integer status;
}
