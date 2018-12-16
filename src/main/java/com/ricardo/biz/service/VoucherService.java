package com.ricardo.biz.service;

import com.ricardo.biz.mapper.entity.Voucher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ricardo
 * @date 2018/12/2
 * 商品兑换券相关服务
 */
@Service
@Slf4j
public class VoucherService {

    /*@Autowired
    private VoucherMapper voucherMapper;

    public Integer create(Voucher voucher) {
        log.info("enter method VoucherService.create: voucher=[{}]", voucher);
        voucherMapper.insertSelective(voucher);
        return voucher.getId();
    }*/
}
