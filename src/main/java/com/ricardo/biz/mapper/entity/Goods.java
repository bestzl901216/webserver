package com.ricardo.biz.mapper.entity;

import com.ricardo.biz.mapper.handlers.GoodsStatusEnumTypeHandler;
import com.ricardo.common.BaseEntity;
import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ricardo
 * @date 2018/12/11
 */
@Data
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
        /** 对应商品上下架状态 **/
        OFF_SHELVES((byte)0, "下架"), ON_SHELVES((byte)1, "上架");

        private String label;
        private byte value;

        StatusEnum(byte value, String label) {
            this.value = value;
            this.label = label;
        }

    }

}
