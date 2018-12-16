package com.ricardo.biz.mapper.handlers;

import com.ricardo.biz.mapper.entity.Goods;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;

/**
 * @author Ricardo
 * @date 2018/12/15
 */
public class GoodsStatusEnumTypeHandler extends EnumOrdinalTypeHandler<Goods.StatusEnum> {

    public GoodsStatusEnumTypeHandler(Class<Goods.StatusEnum> type) {
        super(type);
    }
}
