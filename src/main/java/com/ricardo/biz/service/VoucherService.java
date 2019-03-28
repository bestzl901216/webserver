package com.ricardo.biz.service;

import com.ricardo.account.AccountUtils;
import com.ricardo.biz.mapper.VoucherMapper;
import com.ricardo.biz.mapper.entity.Voucher;
import com.ricardo.utils.DateUtils;
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

    private VoucherMapper voucherMapper;

    @Autowired
    private VoucherService(VoucherMapper voucherMapper) {
        this.voucherMapper = voucherMapper;
    }

    public Integer create(Voucher voucher) {
        log.info("enter method VoucherService.create: voucher=[{}]", voucher);
        voucher.setPurchaseUid(AccountUtils.getCurrentUid());
        voucher.setPurchaseTime(DateUtils.getCurrentSeconds());
        voucher.setStatus(Voucher.StatusEnum.FREEZE);
        voucher.addCreateInfo();
        voucherMapper.insertSelective(voucher);
        log.info("leave method VoucherService.create: id=[{}]", voucher.getId());
        return voucher.getId();
    }

    public String getCdKey(Voucher voucher) {
        final String salt = "orange";
        String[] elements = {voucher.getId()+""};
        return "";
    }
}
