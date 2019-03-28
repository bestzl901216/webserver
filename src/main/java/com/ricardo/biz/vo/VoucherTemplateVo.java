package com.ricardo.biz.vo;

import com.ricardo.biz.mapper.entity.VoucherTemplate;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ricardo
 * @date 2018/12/12
 */
@Data
public class VoucherTemplateVo {

    /** 兑换券种类id **/
    private Integer id;
    /** 商品id集合 **/
    private List<Integer> goodsIdList;
    /** 商品数量集合 **/
    private List<Integer> goodsQuantityList;
    /** 零售价 **/
    private BigDecimal price;
    /** 兑换券种类状态 **/
    private VoucherTemplate.StatusEnum status;
}
