package com.ricardo.biz.dto;

import com.ricardo.common.BasePageDto;
import lombok.Data;

/**
 * @author Ricardo
 * @date 2019/5/1
 */
@Data
public class VoucherTemplateListByPageDto extends BasePageDto {

    private Integer id;
    /**
     * 名称
     **/
    private String name;
}
