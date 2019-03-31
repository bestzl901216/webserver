package com.ricardo.biz.dto;

import com.ricardo.biz.mapper.entity.VoucherTemplate;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ricardo
 * @date 2018/12/10
 */
@Data
public class VoucherTemplateCreateDto {

    /** 兑换券id **/
    @NotNull
    @Min(1)
    @Max(1000000000)
    private Integer id;
    /** 商品信息集合 **/
    @NotEmpty
    private List<VoucherTemplate.GoodsInfo> goodsInfoList;
    /** 零售价 **/
    @NotNull
    @DecimalMin("0.00")
    private BigDecimal price;

}
