package com.ricardo.biz.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ricardo
 * @date 2018/12/10
 */
@Data
public class VoucherTemplateCreateDto {

    /** 商品id集合 **/
    @NotEmpty
    private List<Integer> goodsIdList;
    /** 商品数量集合 **/
    @NotEmpty
    private List<Integer> goodsQuantityList;
    /** 零售价 **/
    @NotNull
    @DecimalMin("0.00")
    private BigDecimal price;

}
