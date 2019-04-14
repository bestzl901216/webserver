package com.ricardo.biz.mapper.entity;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ricardo.biz.mapper.handlers.GoodsStatusEnumTypeHandler;
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
 * @date 2018/12/11
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "goods")
public class Goods extends BaseEntity {

    /** 商品id **/
    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    /** 名称 **/
    private String name;
    /** 商品规格 **/
    private String specification;
    /** 厂商 **/
    private String manufacturer;
    /** 商品状态 **/
    @ColumnType(typeHandler = GoodsStatusEnumTypeHandler.class)
    private StatusEnum status;

    public enum StatusEnum {
        /** 对应商品上架状态 **/
        OFF_SHELVES((byte) 0, "下架"),
        /** 对应商品下架状态 **/
        ON_SHELVES((byte) 1, "上架");

        private byte value;
        private String label;

        public static final Set<DictItem> DICT;
        public static final Map<Byte, StatusEnum> MAP;

        static {
            Set<DictItem> tempSet = Sets.newHashSet();
            Map<Byte, StatusEnum> tempMap = Maps.newHashMap();
            StatusEnum[] enums = StatusEnum.values();
            for(StatusEnum item : enums) {
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
