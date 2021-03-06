package com.ricardo.biz.controller;

import com.ricardo.biz.dto.GoodsCreateDto;
import com.ricardo.biz.dto.GoodsListByPageDto;
import com.ricardo.biz.mapper.entity.Goods;
import com.ricardo.biz.service.GoodsService;
import com.ricardo.biz.vo.GoodsVo;
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
 * 商品管理页面
 */
@Api(tags = "商品资源接口")
@Slf4j
@RestController
@RequestMapping("goods")
public class GoodsController {

    private GoodsService goodsService;

    @Autowired
    private GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @ApiOperation("创建商品")
    @PostMapping("")
    public Integer create(@RequestBody @Valid GoodsCreateDto dto) {
        log.info("enter method GoodsController.create: dto=[{}]", dto);
        Goods goods = DozerUtils.map(dto, Goods.class);
        return goodsService.create(goods);
    }

    @ApiOperation("根据id获取商品信息")
    @GetMapping("/{id}")
    public GoodsVo getGoodsById(@PathVariable Integer id) {
        log.info("enter method GoodsController.getGoodsById: id=[{}]", id);
        Goods goods = goodsService.getGoodsById(id);
        log.info("goods=[{}]", goods);
        return DozerUtils.map(goods, GoodsVo.class);
    }

    @ApiOperation("分页查询商品信息")
    @GetMapping("")
    public PageResult<GoodsVo> listGoodsByPage(@NotNull @Valid GoodsListByPageDto dto) {
        log.info("enter method GoodsController.listGoodsByPage: dto=[{}]", dto);
        Goods goods = DozerUtils.map(dto, Goods.class);
        PageResult<Goods> pageResult = goodsService.listGoodsByPage(goods, dto.getPage(), dto.getSize());
        log.info("pageResult=[{}]", pageResult);
        return new PageResult<>(pageResult.getTotal(), DozerUtils.map(pageResult.getRows(), GoodsVo.class));
    }

    @ApiOperation("获取商品状态字典")
    @GetMapping("/statusDict")
    public Set<DictItem> getGoodsStatusDict() {
        log.info("enter method GoodsController.getGoodsStatusDict");
        return Goods.StatusEnum.DICT;
    }

    // 更新商品
    // 删除商品
    // 启用商品
    // 废弃商品
}
