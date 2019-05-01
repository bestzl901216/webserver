package com.ricardo.biz.controller;

import com.ricardo.biz.dto.VoucherTemplateCreateDto;
import com.ricardo.biz.dto.VoucherTemplateListByPageDto;
import com.ricardo.biz.mapper.entity.VoucherTemplate;
import com.ricardo.biz.service.VoucherTemplateService;
import com.ricardo.biz.vo.VoucherTemplateVo;
import com.ricardo.common.PageResult;
import com.ricardo.utils.DozerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Ricardo
 * @date 2019/3/27
 */
@Api(tags = "兑换券模板资源接口")
@Slf4j
@RestController
@RequestMapping("voucherTemplate")
public class VoucherTemplateController {

    private VoucherTemplateService voucherTemplateService;

    @Autowired
    private VoucherTemplateController(VoucherTemplateService voucherTemplateService) {
        this.voucherTemplateService = voucherTemplateService;
    }

    @ApiOperation("新建兑换券模板")
    @PostMapping("")
    public Integer create(@RequestBody @Valid VoucherTemplateCreateDto dto) {
        log.info("enter method VoucherTemplateController.create: dto=[{}]", dto);
        VoucherTemplate voucherTemplate = DozerUtils.map(dto, VoucherTemplate.class);
        return voucherTemplateService.create(voucherTemplate);
    }

    @ApiOperation("根据id获取兑换券模板信息")
    @GetMapping("/{id}")
    public VoucherTemplateVo getVoucherTemplateById(@PathVariable Integer id) {
        log.info("enter method VoucherTemplateController.getVoucherTemplateById: id=[{}]", id);
        VoucherTemplate voucherTemplate = voucherTemplateService.getVoucherTemplateById(id);
        log.info("voucherTemplate=[{}]", voucherTemplate);
        return DozerUtils.map(voucherTemplate, VoucherTemplateVo.class);
    }

    // 更新兑换券模板

    @ApiOperation("分页查询兑换券模板")
    @GetMapping("")
    public PageResult<VoucherTemplateVo> listVoucherTemplateByPage(@NotNull @Valid VoucherTemplateListByPageDto dto) {
        log.info("enter method VoucherTemplateController.listGoodsByPage: dto=[{}]", dto);
        VoucherTemplate voucherTemplate = DozerUtils.map(dto, VoucherTemplate.class);
        PageResult<VoucherTemplate> pageResult = voucherTemplateService.listVoucherTemplateByPage(voucherTemplate, dto.getPage(), dto.getSize());
        log.info("pageResult=[{}]", pageResult);
        return new PageResult<>(pageResult.getTotal(), DozerUtils.map(pageResult.getRows(), VoucherTemplateVo.class));
    }
}
