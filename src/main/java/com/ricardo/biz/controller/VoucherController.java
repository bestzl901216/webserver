package com.ricardo.biz.controller;

import com.ricardo.biz.dto.VoucherCreateDto;
import com.ricardo.biz.mapper.entity.Voucher;
import com.ricardo.biz.service.VoucherService;
import com.ricardo.utils.MyObjectUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Ricardo
 * @date 2018/11/29
 */
@Api(tags = "兑换券资源接口")
@Slf4j
@RestController
@RequestMapping("voucher")
public class VoucherController {

    private VoucherService voucherService;

    @Autowired
    private VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @ApiOperation("创建兑换券")
    @PostMapping("")
    public Integer create(@RequestBody @Valid VoucherCreateDto dto) {
        log.info("enter method VoucherController.create: dto=[{}]", dto);
        Voucher voucher = MyObjectUtils.transform(dto, Voucher.class);
        return voucherService.create(voucher);
    }
}
