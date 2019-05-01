package com.ricardo.biz.vo;

import com.ricardo.biz.mapper.entity.Voucher;
import lombok.Data;

/**
 * @author Ricardo
 * @date 2019/5/1
 */
@Data
public class VoucherVo {

    /**
     * 兑换券id
     **/
    private Integer id;
    /**
     * 兑换券模板id
     **/
    private Integer voucherTemplateId;
    /**
     * 持有人id
     **/
    private Integer possessUid;
    /**
     * 持有时间
     **/
    private Integer possessTime;
    /**
     * 兑换者id
     **/
    private Integer exchangeUid;
    /**
     * 兑换时间
     **/
    private Integer exchangeTime;
    /**
     * 兑换券状态，冻结 生效 失效
     **/
    private Voucher.StatusEnum status;
}
