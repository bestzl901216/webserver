package com.ricardo.biz.service;

import com.ricardo.biz.mapper.VoucherTemplateMapper;
import com.ricardo.biz.mapper.entity.VoucherTemplate;
import com.ricardo.common.PageResult;
import com.ricardo.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ricardo
 * @date 2019/1/11
 */
@Service
@Slf4j
public class VoucherTemplateService {

    private VoucherTemplateMapper voucherTemplateMapper;

    @Autowired
    private VoucherTemplateService(VoucherTemplateMapper voucherTemplateMapper) {
        this.voucherTemplateMapper = voucherTemplateMapper;
    }

    /**
     * 新建兑换券模板
     * @param voucherTemplate 兑换券模板
     * @return 兑换券模板id
     */
    public Integer create(VoucherTemplate voucherTemplate) {
        log.info("enter method VoucherTemplateService.create: voucherCategory=[{}]", voucherTemplate);
        checkGoodsInfo(voucherTemplate);
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


    private void checkGoodsInfo(VoucherTemplate voucherTemplate) {
        if (voucherTemplate.getGoodsIdList().size() != voucherTemplate.getGoodsQuantityList().size()) {
            throw new RuntimeException("兑换券模板中商品信息错误");
        }
    }
}
