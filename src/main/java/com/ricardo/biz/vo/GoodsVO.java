package com.ricardo.biz.vo;

import com.ricardo.biz.mapper.entity.Goods;
import lombok.Data;

/**
 * @author Ricardo
 * @date 2018/12/16
 */
@Data
public class GoodsVO {

    /** id **/
    private Integer id;
    /** 名称 **/
    private String name;
    /** 规格 **/
    private String specification;
    /** 厂商 **/
    private String manufacturer;
    /** 状态 **/
    private Goods.StatusEnum status;

}
