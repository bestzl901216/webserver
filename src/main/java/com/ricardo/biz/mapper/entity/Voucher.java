package com.ricardo.biz.mapper.entity;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ricardo.biz.mapper.handlers.VoucherStatusEnumTypeHandler;
import com.ricardo.common.BaseEntity;
import com.ricardo.common.DictItem;
import lombok.*;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author Ricardo
 * @date 2018/12/2
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voucher")
public class Voucher extends BaseEntity {

    /** 兑换券id **/
    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    /** 兑换券模板id **/
    private Integer voucherTemplateId;
    /**
     * 持有人id
     **/
    private Integer possessUid;
    /**
     * 持有时间
     **/
    private Integer possessTime;
    /** 兑换者id **/
    private Integer exchangeUid;
    /** 兑换时间 **/
    private Integer exchangeTime;
    /** 兑换券状态，冻结 生效 失效 **/
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
        public static final Map<Byte, StatusEnum> MAP;

        static {
            Set<DictItem> tempSet = Sets.newHashSet();
            Map<Byte, StatusEnum> tempMap = Maps.newHashMap();
            Voucher.StatusEnum[] enums = Voucher.StatusEnum.values();
            for(Voucher.StatusEnum item : enums) {
                tempSet.add(new DictItem(item.label, item + ""));
                tempMap.put(item.getValue(), item);
            }
            DICT = Collections.unmodifiableSet(tempSet);
            MAP = Collections.unmodifiableMap(tempMap);
        }

        StatusEnum(byte value, String label) {
            this.value = value;
            this.label = label;
        }

        public byte getValue() {
            return this.value;
        }

        public String getLabel() {
            return this.label;
        }

    }
}
