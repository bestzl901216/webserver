package com.ricardo.biz.dto;

import com.ricardo.biz.mapper.entity.Voucher;
import com.ricardo.common.BasePageDto;
import lombok.Data;

/**
 * @author Ricardo
 * @date 2019/5/1
 */
@Data
public class VoucherListByPageDto extends BasePageDto {

    private Integer id;

    private Integer voucherTemplateId;

    private Integer possessUid;

    private Integer exchangeUid;

    private Voucher.StatusEnum status;
}
