package com.ricardo.biz.controller;

import com.ricardo.biz.dto.VoucherCreateDto;
import com.ricardo.biz.dto.VoucherListByPageDto;
import com.ricardo.biz.mapper.entity.Voucher;
import com.ricardo.biz.service.VoucherService;
import com.ricardo.biz.vo.VoucherVo;
import com.ricardo.common.DictItem;
import com.ricardo.common.PageResult;
import com.ricardo.utils.DozerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

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
        Voucher voucher = DozerUtils.map(dto, Voucher.class);
        return voucherService.create(voucher);
    }

    @ApiOperation("查看兑换码")
    @GetMapping("/{id}/cdKey")
    public String viewCdKey(@PathVariable Integer id) {
        log.info("enter method VoucherController.viewCdKey: id=[{}]", id);
        return voucherService.viewCdKey(id);
    }

    @ApiOperation("使用兑换券")
    @PostMapping("action=exchange")
    public void exchange(@RequestBody @Valid String cdKey) {
        log.info("enter method VoucherController.exchange: cdKey=[{}]", cdKey);
        voucherService.exchange(cdKey);
    }

    @ApiOperation("分页查询兑换券")
    @GetMapping("")
    public PageResult<VoucherVo> listVoucherByPage(@NotNull @Valid VoucherListByPageDto dto) {
        log.info("enter method VoucherController.listVoucherByPage: dto=[{}]", dto);
        Voucher voucher = DozerUtils.map(dto, Voucher.class);
        PageResult<Voucher> pageResult = voucherService.listVoucherByPage(voucher, dto.getPage(), dto.getSize());
        log.info("pageResult=[{}]", pageResult);
        return new PageResult<>(pageResult.getTotal(), DozerUtils.map(pageResult.getRows(), VoucherVo.class));
    }

    @ApiOperation("获取兑换券状态字典")
    @GetMapping("/statusDict")
    public Set<DictItem> getVoucherStatusDict() {
        log.info("enter method VoucherController.getVoucherStatusDict");
        return Voucher.StatusEnum.DICT;
    }
}
