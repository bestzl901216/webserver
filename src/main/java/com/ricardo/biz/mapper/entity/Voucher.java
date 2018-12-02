package com.ricardo.biz.mapper.entity;

import com.ricardo.biz.enums.VoucherEnum;
import com.ricardo.common.BaseEntity;
import lombok.Data;

/**
 * @author Ricardo
 * @date 2018/12/2
 */
@Data
public class Voucher extends BaseEntity {

    /** 礼品券id **/
    private Integer id;
    /** 礼品券编码 **/
    private String code;
    /** 礼品券卡密 **/
    private String cdKey;
    /** 礼品券状态，冻结 生效 失效 **/
    private VoucherEnum status;
    /** 商品id,外键连接goods表 **/
    private Integer goodsId;
    /** 商品数量 **/
    private Integer goodsQuantity;

}
