package com.ricardo.biz.mapper.entity;

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
    /** 礼品券种类id，外键连接VoucherCategory表 **/
    private Integer voucherCategoryId;
    /** 礼品券编码 **/
    private String code;
    /** 礼品券卡密 **/
    private String cdKey;
    /** 礼品券状态，冻结 生效 失效 **/
    private Integer status;

}
