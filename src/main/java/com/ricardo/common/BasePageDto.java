package com.ricardo.common;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Ricardo
 * @date 2018/12/16
 */
@Data
public abstract class BasePageDto {

    @Min(1)
    private Integer page;
    @Min(1)
    @Max(50)
    private Integer size;
}
