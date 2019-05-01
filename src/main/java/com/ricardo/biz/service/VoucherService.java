package com.ricardo.biz.service;

import com.ricardo.account.AccountUtils;
import com.ricardo.biz.mapper.VoucherMapper;
import com.ricardo.biz.mapper.entity.Voucher;
import com.ricardo.common.PageResult;
import com.ricardo.utils.AesUtils;
import com.ricardo.utils.MyDateUtils;
import com.ricardo.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Ricardo
 * @date 2018/12/2
 * 商品兑换券相关服务
 */
@Service
@Slf4j
public class VoucherService {

    private VoucherMapper voucherMapper;

    private static final String PASSWORD = "goods-voucher";

    private static final String DELIMITER = "-";

    @Autowired
    private VoucherService(VoucherMapper voucherMapper) {
        this.voucherMapper = voucherMapper;
    }

    public Integer create(Voucher voucher) {
        log.info("enter method VoucherService.create: voucher=[{}]", voucher);
        voucher.setPossessUid(AccountUtils.getCurrentUid());
        voucher.setPossessTime(MyDateUtils.getCurrentSeconds());
        voucher.setStatus(Voucher.StatusEnum.FREEZE);
        voucher.addCreateInfo();
        voucherMapper.insertSelective(voucher);
        log.info("leave method VoucherService.create: id=[{}]", voucher.getId());
        return voucher.getId();
    }

    public String viewCdKey(Integer id) {
        Voucher voucher = this.getById(id);
        Integer uid = AccountUtils.getCurrentUid();
        if (!voucher.getPossessUid().equals(uid)) {
            throw new RuntimeException("非法查看不属于当前用户的兑换券cdKey");
        } else {
            return this.getCdKey(voucher);
        }
    }

    public PageResult<Voucher> listVoucherByPage(Voucher voucher, Integer page, Integer size) {
        log.info("enter method VoucherService.listVoucherByPage: voucher=[{}],page=[{}],size=[{}]", voucher, page, size);
        PageResult<Voucher> result = PageUtils.getEmptyPageResult(Voucher.class);
        int total = voucherMapper.selectCount(voucher);
        if (total != 0) {
            List<Voucher> rows = voucherMapper.selectByRowBounds(voucher, PageUtils.getRowBounds(page, size));
            result = new PageResult<>(total, rows);
        }
        log.info("leave method VoucherService.listVoucherByPage: total=[{}]", total);
        return result;
    }

    public void exchange(String cdKey) {
        // 1.cdKey转换为兑换券信息
        Voucher cdKeyVoucher = cdKey2Voucher(cdKey);
        // 2.获取数据库中兑换券信息
        Voucher voucher = getById(cdKeyVoucher.getId());
        // 3.检查兑换券信息
        this.checkValidity(cdKeyVoucher, voucher);
        // 4.更新兑换信息
        voucher.setExchangeUid(AccountUtils.getCurrentUid());
        voucher.setExchangeTime(MyDateUtils.getCurrentSeconds());
        voucher.setStatus(Voucher.StatusEnum.EFFECTIVE);
        Example example = new Example(Voucher.class);
        example.createCriteria().andEqualTo("id", voucher.getId())
                .andEqualTo("status", Voucher.StatusEnum.FREEZE.getValue());
        this.update(voucher, example);
    }

    private Voucher getById(Integer id) {
        log.info("enter method VoucherService.getById: id=[{}]", id);
        Voucher voucher = voucherMapper.selectByPrimaryKey(id);
        if (voucher == null) {
            throw new RuntimeException("目标资源信息不存在");
        }
        log.info("leave method VoucherService.getById: voucher=[{}]", voucher);
        return voucher;
    }

    private String getCdKey(Voucher voucher) {
        // 1.拼接待加密的字符串 id-voucherTemplateId-purchaseUid-purchaseTime
        String[] temp = {voucher.getId() + "", voucher.getVoucherTemplateId() + "", voucher.getPossessUid() + "", voucher.getPossessTime() + ""};
        String clearText = String.join(DELIMITER, temp);
        // 2.AES加密
        String cipherText = AesUtils.encryptHex(clearText, PASSWORD);
        // 3.打乱加密后字符串的顺序并返回结果
        return StringUtils.reverse(cipherText);
    }

    private void update(Voucher voucher, Example example) {
        voucher.setUpdateUser(AccountUtils.getCurrentUid());
        voucher.setUpdateTime(MyDateUtils.getCurrentSeconds());
        voucherMapper.updateByExampleSelective(voucher, example);
    }

    private Voucher cdKey2Voucher(String cdKey) {
        // 1.恢复正确顺序
        String cipherText = StringUtils.reverse(cdKey);
        // 2.AES解密
        String clearText = AesUtils.decryptHex(cipherText, PASSWORD);
        // 3.生成兑换券信息
        String[] temp = clearText.split(DELIMITER);
        return Voucher.builder()
                .id(Integer.valueOf(temp[0]))
                .voucherTemplateId(Integer.valueOf(temp[1]))
                .possessUid(Integer.valueOf(temp[2]))
                .possessTime(Integer.valueOf(temp[3]))
                .build();
    }

    private void checkValidity(Voucher cdKeyVoucher, Voucher voucher) {
        if (!cdKeyVoucher.getVoucherTemplateId().equals(voucher.getVoucherTemplateId()) ||
                !cdKeyVoucher.getPossessUid().equals(voucher.getPossessUid()) ||
                !cdKeyVoucher.getPossessTime().equals(voucher.getPossessTime()) ||
                voucher.getStatus() != Voucher.StatusEnum.FREEZE) {
            throw new RuntimeException("无效cdKey");
        }
    }
}
