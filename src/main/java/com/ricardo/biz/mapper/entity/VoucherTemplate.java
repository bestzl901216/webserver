package com.ricardo.biz.mapper.entity;

import com.google.common.collect.Sets;
import com.ricardo.biz.mapper.handlers.IntegerListToStringTypeHandler;
import com.ricardo.biz.mapper.handlers.VoucherTemplateStatusEnumTypeHandler;
import com.ricardo.common.BaseEntity;
import com.ricardo.common.DictItem;
import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Ricardo
 * @date 2018/12/12
 */
@Setter
@Getter
@Table(name = "voucher_template")
public class VoucherTemplate extends BaseEntity {

    /** 兑换券模板id **/
    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    /** 商品id集合 **/
    @ColumnType(typeHandler = IntegerListToStringTypeHandler.class)
    private List<Integer> goodsIdList;
    /** 商品数量集合 **/
    @ColumnType(typeHandler = IntegerListToStringTypeHandler.class)
    private List<Integer> goodsQuantityList;
    /** 零售价 **/
    private BigDecimal price;
    /** 兑换券模板状态 **/
    @ColumnType(typeHandler = VoucherTemplateStatusEnumTypeHandler.class)
    private StatusEnum status;

    public enum StatusEnum {
        /** 对应兑换券模板上架状态 **/
        OFF_SHELVES((byte) 0, "下架"),
        /** 对应兑换券模板下架状态 **/
        ON_SHELVES((byte) 1, "上架");

        private byte value;
        private String label;

        public static final Set<DictItem> DICT;

        static {
            Set<DictItem> tempSet = Sets.newHashSet();
            VoucherTemplate.StatusEnum[] enums = VoucherTemplate.StatusEnum.values();
            for(VoucherTemplate.StatusEnum item : enums) {
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
