package com.ricardo.biz.controller;

import com.ricardo.biz.dto.GoodsCreateDTO;
import com.ricardo.biz.dto.GoodsListByPageDTO;
import com.ricardo.biz.mapper.entity.Goods;
import com.ricardo.biz.service.GoodsService;
import com.ricardo.biz.vo.GoodsVO;
import com.ricardo.common.PageResult;
import com.ricardo.utils.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Ricardo
 * @date 2018/11/29
 * 商品管理页面
 */
@Slf4j
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("")
    public Integer create(@RequestBody @Valid GoodsCreateDTO dto) {
        log.info("enter method GoodsController.create: dto=[{}]", dto);
        Goods goods = ObjectUtil.transform(dto, Goods.class);
        goodsService.create(goods);
        return goods.getId();
    }

    @GetMapping("/{id}")
    public GoodsVO getGoodsById(@PathVariable Integer id) {
        log.info("enter method GoodsController.getGoodsById: id=[{}]", id);
        Goods goods = goodsService.getGoodsById(id);
        log.info("goods=[{}]", goods);
        return ObjectUtil.transform(goods, GoodsVO.class);
    }

    @GetMapping("")
    public PageResult<GoodsVO> listGoodsByPage(@NotNull @Valid GoodsListByPageDTO dto) {
        log.info("enter method GoodsController.listGoodsByPage: dto=[{}]", dto);
        Goods goods = ObjectUtil.transform(dto, Goods.class);
        PageResult<Goods> pageResult = goodsService.listGoodsByPage(goods, dto.getPage(), dto.getSize());
        log.info("pageResult=[{}]", pageResult);
        return new PageResult<>(pageResult.getTotal(), ObjectUtil.batchTransform(pageResult.getRows(), GoodsVO.class));
    }
}
