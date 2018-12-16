package com.ricardo.biz.service;

import com.google.common.collect.Lists;
import com.ricardo.biz.mapper.GoodsMapper;
import com.ricardo.biz.mapper.entity.Goods;
import com.ricardo.common.PageResult;
import com.ricardo.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ricardo
 * @date 2018/12/2
 * 商品管理相关服务
 */
@Service
@Slf4j
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 添加商品信息
     * @param goods
     * @return 商品信息id
     */
    public Integer create(Goods goods) {
        log.info("enter method GoodsService.create: goods=[{}]", goods);
        goods.setStatus(Goods.StatusEnum.OFF_SHELVES);
        goods.addCreateInfo();
        goodsMapper.insertSelective(goods);
        return goods.getId();
    }

    /**
     * 根据主键id查询商品信息
     * @param id
     * @return 商品信息
     * @exception RuntimeException 信息不存在
     */
    public Goods getGoodsById(Integer id) {
        log.info("enter method GoodsService.getGoodsById: id=[{}]", id);
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        if (goods == null) {
            throw new RuntimeException("目标资源信息不存在");
        }
        return goods;
    }

    /**
     * 根据条件分页查询商品信息
     * @param goods 查询条件
     * @param page 页码
     * @param size 页长
     * @return
     */
    public PageResult<Goods> listGoodsByPage(Goods goods, Integer page, Integer size) {
        log.info("enter method GoodsService.listGoodsByPage: goods=[{}],page=[{}],size=[{}]", goods, page, size);
        int total = goodsMapper.selectCount(goods);
        if (total == 0) {
            return new PageResult<>(total, Lists.newArrayList());
        } else {
            List<Goods> rows = goodsMapper.selectByRowBounds(goods, PageUtil.getRowBounds(page, size));
            return new PageResult<>(total, rows);
        }
    }
}
