package com.ricardo.common;

import lombok.Data;

/**
 * @author Ricardo
 * @date 2018/12/1
 */
@Data
public abstract class BaseEntity {

    private Integer createTime;

    private Integer createUser;

    private Integer updateTime;

    private Integer updateUser;
}
