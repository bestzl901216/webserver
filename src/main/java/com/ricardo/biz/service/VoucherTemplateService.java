package com.ricardo.biz.service;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ricardo.biz.mapper.VoucherTemplateMapper;
import com.ricardo.biz.mapper.entity.VoucherTemplate;
import com.ricardo.common.PageResult;
import com.ricardo.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Ricardo
 * @date 2019/1/11
 */
@Service
@Slf4j
public class VoucherTemplateService {

    private VoucherTemplateMapper voucherTemplateMapper;

    private GoodsService goodsService;

    @Autowired
    private VoucherTemplateService(VoucherTemplateMapper voucherTemplateMapper,
                                   GoodsService goodsService) {
        this.voucherTemplateMapper = voucherTemplateMapper;
        this.goodsService =  goodsService;
    }

    /**
     * 新建兑换券模板
     * @param voucherTemplate 兑换券模板
     * @return 兑换券模板id
     */
    public Integer create(VoucherTemplate voucherTemplate) {
        log.info("enter method VoucherTemplateService.create: voucherTemplate=[{}]", voucherTemplate);
        Set<Integer> goodsIdSet = this.preProcessGoodsInfo(voucherTemplate.getGoodsInfoList());
        goodsService.checkGoods(goodsIdSet);
        voucherTemplate.setStatus(VoucherTemplate.StatusEnum.OFF_SHELVES);
        voucherTemplate.addCreateInfo();
        voucherTemplateMapper.insertSelective(voucherTemplate);
        log.info("leave method VoucherTemplateService.create: id=[{}]", voucherTemplate.getId());
        return voucherTemplate.getId();
    }

    /**
     * 根据主键id查询兑换券模板
     * @param id 主键id
     * @return VoucherTemplate 兑换券模板
     * @exception RuntimeException 信息不存在
     */
    public VoucherTemplate getVoucherTemplateById(Integer id) {
        log.info("enter method VoucherTemplateService.getVoucherTemplateById: id=[{}]", id);
        VoucherTemplate voucherTemplate = voucherTemplateMapper.selectByPrimaryKey(id);
        if (voucherTemplate == null) {
            throw new RuntimeException("目标资源信息不存在");
        }
        log.info("leave method VoucherTemplateService.getVoucherTemplateById: voucherTemplate=[{}]", voucherTemplate);
        return voucherTemplate;
    }

    /**
     * 根据条件分页查询兑换券模板信息
     * @param voucherTemplate 查询条件
     * @param page 页码
     * @param size 页长
     * @return 分页查询结果
     */
    public PageResult<VoucherTemplate> listVoucherTemplateByPage(VoucherTemplate voucherTemplate, Integer page, Integer size) {
        log.info("enter method VoucherTemplateService.listVoucherTemplateByPage: voucherTemplate=[{}],page=[{}],size=[{}]", voucherTemplate, page, size);
        PageResult<VoucherTemplate> result = PageUtils.getEmptyPageResult(VoucherTemplate.class);
        int total = voucherTemplateMapper.selectCount(voucherTemplate);
        if (total != 0) {
            List<VoucherTemplate> rows = voucherTemplateMapper.selectByRowBounds(voucherTemplate, PageUtils.getRowBounds(page, size));
            result = new PageResult<>(total, rows);
        }
        log.info("leave method VoucherTemplateService.listVoucherTemplateByPage: total=[{}]", total);
        return result;
    }

    /**
     * 创建兑换券模板前，预处理关联的商品信息列表。将相同goodsId的goodsInfo的数量信息相加。
     * @param goodsInfoList 商品信息列表
     * @return 商品id集合
     */
    private Set<Integer> preProcessGoodsInfo(List<VoucherTemplate.GoodsInfo> goodsInfoList) {
        log.info("enter method VoucherTemplateService.preProcessGoodsInfo: goodsInfoList=[{}]", goodsInfoList);
        Set<Integer> goodsIdSet = Sets.newLinkedHashSet();
        Map<Integer, VoucherTemplate.GoodsInfo> goodsInfoMap = Maps.newHashMap();
        for (VoucherTemplate.GoodsInfo goodsInfo : goodsInfoList) {
            Integer goodsId = goodsInfo.getGoodsId();
            if (!goodsIdSet.contains(goodsId)) {
                goodsIdSet.add(goodsId);
                goodsInfoMap.put(goodsId, goodsInfo);
            } else {
                VoucherTemplate.GoodsInfo e = goodsInfoMap.get(goodsId);
                e.setGoodsQuantity(e.getGoodsQuantity() + goodsInfo.getGoodsQuantity());
            }
        }
        goodsInfoList.clear();
        goodsIdSet.forEach(goodsId -> goodsInfoList.add(goodsInfoMap.get(goodsId)));
        log.info("leave method VoucherTemplateService.preProcessGoodsInfo: goodsInfoList=[{}]", goodsInfoList);
        return goodsIdSet;
    }

}
