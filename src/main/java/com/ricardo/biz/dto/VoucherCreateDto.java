package com.ricardo.biz.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Ricardo
 * @date 2018/12/10
 */
@Data
public class VoucherCreateDto {

    /** 兑换券模板id **/
    @Min(1)
    @NotNull
    private Integer voucherTemplateId;

}
