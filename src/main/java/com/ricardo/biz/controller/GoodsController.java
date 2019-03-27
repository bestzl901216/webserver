package com.ricardo.biz.controller;

import com.ricardo.biz.dto.GoodsCreateDto;
import com.ricardo.biz.dto.GoodsListByPageDto;
import com.ricardo.biz.mapper.entity.Goods;
import com.ricardo.biz.service.GoodsService;
import com.ricardo.biz.vo.GoodsVo;
import com.ricardo.common.DictItem;
import com.ricardo.common.PageResult;
import com.ricardo.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Ricardo
 * @date 2018/11/29
 * 商品管理页面
 */
@Slf4j
@RestController
@RequestMapping("goods")
public class GoodsController {

    private GoodsService goodsService;

    @Autowired
    private GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @PostMapping("")
    public Integer create(@RequestBody @Valid GoodsCreateDto dto) {
        log.info("enter method GoodsController.create: dto=[{}]", dto);
        Goods goods = ObjectUtils.transform(dto, Goods.class);
        return goodsService.create(goods);
    }

    @GetMapping("/{id}")
    public GoodsVo getGoodsById(@PathVariable Integer id) {
        log.info("enter method GoodsController.getGoodsById: id=[{}]", id);
        Goods goods = goodsService.getGoodsById(id);
        log.info("goods=[{}]", goods);
        return ObjectUtils.transform(goods, GoodsVo.class);
    }

    @GetMapping("")
    public PageResult<GoodsVo> listGoodsByPage(@NotNull @Valid GoodsListByPageDto dto) {
        log.info("enter method GoodsController.listGoodsByPage: dto=[{}]", dto);
        Goods goods = ObjectUtils.transform(dto, Goods.class);
        PageResult<Goods> pageResult = goodsService.listGoodsByPage(goods, dto.getPage(), dto.getSize());
        log.info("pageResult=[{}]", pageResult);
        return new PageResult<>(pageResult.getTotal(), ObjectUtils.batchTransform(pageResult.getRows(), GoodsVo.class));
    }

    @GetMapping("/statusDict")
    public Set<DictItem> getGoodsStatusDict() {
        log.info("enter method GoodsController.getGoodsStatusDict");
        return Goods.StatusEnum.DICT;
    }
}
