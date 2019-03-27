package com.ricardo.biz.mapper.handlers;

import com.ricardo.biz.mapper.entity.Voucher;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;

/**
 * @author Ricardo
 * @date 2018/12/15
 */
public class VoucherStatusEnumTypeHandler extends EnumOrdinalTypeHandler<Voucher.StatusEnum> {

    public VoucherStatusEnumTypeHandler(Class<Voucher.StatusEnum> type) {
        super(type);
    }
}
