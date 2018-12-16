package com.ricardo.common;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Ricardo
 * @date 2018/12/16
 */
@Data
public abstract class BasePageDTO {

    @Min(1)
    private Integer page;
    @Min(1)
    @Max(1000)
    private Integer size;
}
