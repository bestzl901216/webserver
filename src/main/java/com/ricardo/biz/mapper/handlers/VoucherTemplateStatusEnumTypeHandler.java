package com.ricardo.biz.mapper.handlers;

import com.ricardo.biz.mapper.entity.VoucherTemplate;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;

/**
 * @author Ricardo
 * @date 2018/12/15
 */
public class VoucherTemplateStatusEnumTypeHandler extends EnumOrdinalTypeHandler<VoucherTemplate.StatusEnum> {

    public VoucherTemplateStatusEnumTypeHandler(Class<VoucherTemplate.StatusEnum> type) {
        super(type);
    }
}
