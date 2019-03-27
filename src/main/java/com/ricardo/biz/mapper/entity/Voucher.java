package com.ricardo.biz.mapper.entity;

import com.google.common.collect.Sets;
import com.ricardo.biz.mapper.handlers.VoucherStatusEnumTypeHandler;
import com.ricardo.common.BaseEntity;
import com.ricardo.common.DictItem;
import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collections;
import java.util.Set;

/**
 * @author Ricardo
 * @date 2018/12/2
 */
@Data
@Table(name = "voucher")
public class Voucher extends BaseEntity {

    /** 礼品券id **/
    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    /** 礼品券模板id **/
    private Integer voucherTemplateId;
    /** 购买者id **/
    private Integer purchaseUid;
    /** 购买时间 **/
    private Integer purchaseTime;
    /** 兑换者id **/
    private Integer exchangeUid;
    /** 兑换时间 **/
    private Integer exchangeTime;
    /** 礼品券状态，冻结 生效 失效 **/
    @ColumnType(typeHandler = VoucherStatusEnumTypeHandler.class)
    private StatusEnum status;

    public enum StatusEnum {
        /** 冻结状态 **/
        FREEZE((byte) 0, "冻结"),
        /** 生效状态 **/
        EFFECTIVE((byte) 1, "生效"),
        /** 失效状态 **/
        INEFFECTIVE((byte) 2, "失效");

        private byte value;
        private String label;

        public static final Set<DictItem> DICT;

        static {
            Set<DictItem> tempSet = Sets.newHashSet();
            Voucher.StatusEnum[] enums = Voucher.StatusEnum.values();
            for(Voucher.StatusEnum item : enums) {
                tempSet.add(new DictItem(item.label, item + ""));
            }
            DICT = Collections.unmodifiableSet(tempSet);
        }

        StatusEnum(byte value, String label) {
            this.value = value;
            this.label = label;
        }

    }
}
